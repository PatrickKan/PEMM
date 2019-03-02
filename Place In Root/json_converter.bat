@echo off
python "..\video_split.py"
bin\OpenPoseDemo.exe --number_people_max 1 --image_dir ../output_images --write_json ../json_output --write_images ../rendered_images --write_images_format jpg
pause