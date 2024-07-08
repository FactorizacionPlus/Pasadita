from machine import Pin, PWM
from led import led
import math
import time

MIN = 270000
MAX = 2240000

PERIOD = 300 # per ms

servo = PWM(Pin(0), freq=50, duty_ns=MIN)

def move_servo_to(percentage: float, sleep = True):
    position_ns = (MAX - MIN) * percentage + MIN

    servo.duty_ns(int(position_ns))

    if sleep:
        current_ns = servo.duty_ns()
        current_perc = (current_ns - MIN) / (MAX - MIN)
        time_ms = math.ceil(abs((percentage - current_perc) * PERIOD))

        time.sleep_ms(int(time_ms) + 200)

def do_a_no():
    move_servo_to(0.75)
    move_servo_to(0.25)
    move_servo_to(0.75)
    move_servo_to(0.25)
    move_servo_to(0)

def open_gate(duration: int):
    led.value(1)
    move_servo_to(0.5) # Set servo at 90°
    time.sleep_ms(duration)
    led.value(0)
    move_servo_to(0, False) # Set servo at initial point
