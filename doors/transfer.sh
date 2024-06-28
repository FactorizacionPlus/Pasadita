#!/bin/zsh

files=("env.py" "led.py" "wifi.py" "servo.py" "main.py")

source ./.venv/bin/activate

mpremote connect $1 + soft-reset

for file in "${files[@]}"; do
  mpremote cp $file ":$file"
done

mpremote disconnect + reset
