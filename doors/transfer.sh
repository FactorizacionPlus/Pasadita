#!/bin/zsh

files=("./env.py" "./led.py" "./wifi.py" "./servo.py" "./main.py")

source ./.venv/bin/activate

for file in "${files[@]}"; do
  rshell -p "$1" --buffer-size 512 cp "$file" "/pyboard/$file"
done
