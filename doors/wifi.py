import network
import time
import socket

from typing import Tuple

wlan = network.WLAN(network.STA_IF)
wlan.active(True)

def wifi_init(ssid: str, password: str):
    wlan.connect(ssid, password)

    for _ in range(10):
        if wlan.status() < 0 or wlan.status() >= 3:
            break
        print("Waiting to connect...")
        time.sleep(1)

    if wlan.status() != 3:
        raise RuntimeError("Network connection failed", wlan.status())

    print(wlan.ifconfig())
    print(wlan.status())

def connect_to(addr: Tuple[str, int] | Tuple[str, int, int, int]) -> socket.socket:
    local_s = socket.socket()
    local_s.settimeout(5)
    local_s.connect(addr)

    local_s.setblocking(False)
    local_s.send(b"GET /sse/terminal/entry HTTP/1.0\r\n\r\n")

    return local_s
