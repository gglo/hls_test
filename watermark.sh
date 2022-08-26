#!/bin/bash


function watermark(){

name=$(basename "$1")
dir=$(dirname "$1")
echo "源文件为："$1
echo "水印文件为："$2
ffmpeg -i "$1" -i "$2" -filter_complex "overlay=x=W-w:y=0" "$dir/${name%.*}-watermark.mp4"
echo "水印添加完成"$dir/${name%.*}-watermark.mp4""

}

#调用函数
watermark $1 $2
