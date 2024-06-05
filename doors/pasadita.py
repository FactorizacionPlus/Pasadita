from machine import Pin, PWM
import socket
import network
import time
import uasyncio as asyncio

SSID = "CLARO1_81DEA0"
PASSWORD = "G61S8ONKBC"
SERVER_ADDRESS = "192.168.1.13"
SERVER_PORT = 8080

# Terminal UUID
# TODO: Maybe try to get this UUID from the server?
TERMINAL = b"eb85ec1b-98ca-43d5-a6d0-605c82ed37a9"

wlan = network.WLAN(network.STA_IF)
wlan.active(True)
wlan.connect(SSID, PASSWORD)

while not wlan.isconnected() and wlan.status() >= 0:
    print("Waiting to connect:")
    time.sleep(1)

print(wlan.ifconfig())

# Socket management

ai = socket.getaddrinfo(SERVER_ADDRESS, SERVER_PORT)
addr = ai[0][-1]

s = socket.socket()
s.connect(addr)
s.send(b"GET /sse/terminal/entry HTTP/1.0\r\n\r\n")

s.setblocking(False)

servo = PWM(Pin(0))
servo.freq(50)
servo.duty_u16(1310) # Set servo at initial point

async def open_gate(duration: int):
    global servo
    led = Pin("LED", Pin.OUT)

    led.value(1)
    servo.duty_u16(4510) # Set servo at 90°
    await asyncio.sleep_ms(duration)
    led.value(0)
    servo.duty_u16(1310) # Set servo at initial point

def parse_event(event: bytes):
    split = event.split(b"\n")

    if (len(split) < 2):
        return None

    data = split[0].split(b":")[1]
    event = split[1].split(b":")[1]

    return (data, event)

async def main():
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

            if (event == b"entry-added" and data == TERMINAL):
                await open_gate(2000)

asyncio.run(main())
