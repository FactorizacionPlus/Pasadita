from machine import Pin, PWM
from led import led
import math
import time

MIN = 260000
MAX = 2240000

PERIOD = 300 # per ms

servo = PWM(Pin(0), freq=50, duty_ns=MIN)

def move_servo_to(percentage: float):
    current_ns = servo.duty_ns()
    current_perc = (current_ns - MIN) / (MAX - MIN)
    position_ns = (MAX - MIN) * percentage + MIN

    time_ms = math.ceil(abs((percentage - current_perc) * PERIOD))

    servo.duty_ns(int(position_ns))
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
    move_servo_to(0) # Set servo at initial point
