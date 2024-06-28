import socket
import network
from servo import open_gate

import env
from wifi import wifi_init

wifi_init(env.SSID, env.PASSWORD)

# Socket management

ai = socket.getaddrinfo(env.SERVER_ADDRESS, env.SERVER_PORT)
addr = ai[0][-1]

print(addr)

s = socket.socket()
s.connect(addr)
s.send(b"GET /sse/terminal/entry HTTP/1.0\r\n\r\n")

s.setblocking(False)

def parse_event(event: bytes):
    split = event.split(b"\n")

    if (len(split) < 2):
        return None

    data = split[0].split(b":")[1]
    event = split[1].split(b":")[1]

    return (data, event)

def main():
    global s

    buffer = b""
    while (True):
        char = s.read(1)
        if (char is None or char is b""):
            continue
        buffer += char

        # Event sent
        if (buffer.endswith("\n\n")):
            if (buffer.startswith("HTTP")):
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
