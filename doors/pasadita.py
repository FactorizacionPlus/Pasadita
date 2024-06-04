from machine import Pin, PWM
import time

led = Pin("LED", Pin.OUT)

servo = PWM(Pin(0))
servo.freq(50)

while True:
    led.value(1)
    servo.duty_u16(1311)
    time.sleep(2)
    led.value(0)
    servo.duty_u16(7864)
    time.sleep(2)
