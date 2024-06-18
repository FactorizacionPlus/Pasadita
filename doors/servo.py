from machine import Pin, PWM
from led import led
import uasyncio as asyncio

servo = PWM(Pin(0), freq=50, duty_u16=1310)

async def open_gate(duration: int):
    led.value(1)
    servo.duty_u16(4510) # Set servo at 90°
    await asyncio.sleep_ms(duration)
    led.value(0)
    servo.duty_u16(1310) # Set servo at initial point
