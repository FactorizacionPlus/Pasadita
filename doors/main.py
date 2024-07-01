import socket
import sys
import env
from servo import open_gate, move_servo_to, do_a_no
from led import blink_led_times
from wifi import wifi_init, wlan, connect_to

for ssid, password in env.WIFI_CONNECTIONS:
    try:
        wifi_init(ssid, password)
        break
    except Exception as e:
        continue

if wlan.status() != 3:
    if (wlan.status() < 0):
        err = abs(wlan.status())
        blink_led_times(err)

    do_a_no()
    sys.exit()
else:
    ssid = wlan.config("ssid")
    print(f"Connected to {ssid}")
    move_servo_to(0.25)
    blink_led_times(1, 500)
    move_servo_to(0)

my_socket: socket.socket | None = None

for address, port in env.SERVER_CONNECTIONS:
    ai = socket.getaddrinfo(address, port)
    addr = ai[0][-1]
    try:
        print(f"Connecting to {addr}...")
        my_socket = connect_to(addr)
        print(f"Connected to {addr}")
        break
    except OSError as e:
        print(e)
        continue

if my_socket is None:
    do_a_no()
    sys.exit()
else:
    move_servo_to(0.25)
    blink_led_times(1, 500)
    move_servo_to(0)

# Event management

def parse_event(event: bytes):
    split = event.split(b"\n")

    if (len(split) < 2):
        return None

    data = split[0].split(b":")[1]
    event = split[1].split(b":")[1]

    return (data, event)

def main():
    buffer = b""
    if my_socket is None:
        return

    while (True):
        char = my_socket.read(1)
        if (char is None or char is b""):
            continue
        buffer += char

        # Event sent
        if (buffer.endswith(b"\n\n")):
            if (buffer.startswith(b"HTTP")):
                buffer = b""
                continue

            parsed = parse_event(buffer)
            buffer = b""
            if (parsed is None):
                continue
            (data, event) = parsed

            print(f"Data: {data}")
            print(f"Event: {event}")
            print()

            if (event == b"entry-added" and data == env.TERMINAL):
                open_gate(2000)

main()
