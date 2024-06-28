import network
import time
from led import blink_led_times
from servo import move_servo_to

def wifi_init(ssid: string, password: string):
    wlan = network.WLAN(network.STA_IF)
    wlan.active(True)
    wlan.connect(ssid, password)

    for _ in range(10):
        if wlan.status() < 0 or wlan.status() >= 3:
            break
        print("Waiting to connect...")
        time.sleep(1)

    if (wlan.status() < 0):
        err = abs(wlan.status())
        blink_led_times(err)
    else:
        blink_led_times(4, 100)

    if wlan.status() != 3:
        raise RuntimeError("Network connection failed", wlan.status())

    print(wlan.ifconfig())
    print(wlan.status())
    move_servo_to(0.5)
    blink_led_times(1, 1000)
    move_servo_to(0)
