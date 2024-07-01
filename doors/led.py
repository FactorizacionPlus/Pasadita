from machine import Pin
import time

led = Pin("LED", Pin.OUT)

def blink_led_times(n: int, duration: int = 250):
    led = Pin("LED", Pin.OUT)
    for _ in range(n):
        led.value(1)
        time.sleep_ms(duration)
        led.value(0)
        time.sleep_ms(duration)

