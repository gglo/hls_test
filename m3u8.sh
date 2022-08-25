#!/bin/bash


function tom3u8(){
for f in "$@"
do
name=$(basename "$f")
dir=$(dirname "$f")
echo "转码文件为："$f
ffmpeg -i "$f" -c copy -hls_time 10 -hls_list_size 0 -f hls -hls_segment_filename "$dir/${name%.*}-%03d.ts" -y "$dir/${name%.*}.m3u8"
echo "已输出播放列表$dir/${name%.*}.m3u8"
echo "切片存储目录$dir"
done
}

#调用函数
tom3u8 $@
