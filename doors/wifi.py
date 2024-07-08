import network
import time
import socket
from cert import context

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

    import ntptime
    ntptime.settime()

def connect_to(addr: Tuple[str, int] | Tuple[str, int, int, int], address: str) -> socket.socket:
    local_s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
    local_s.settimeout(5)
    local_s.connect(addr)

    if (addr[1] == 443):
        local_ss = context.wrap_socket(local_s, server_side=False, server_hostname=address)
        local_ss.setblocking(False)
        return local_ss

    local_s.setblocking(False)
    return local_s
