#!/bin/bash


function watermark(){

echo "源文件为："$1
echo "水印文件为："$2
ffmpeg -i "$1" -i "$2" -filter_complex "overlay=x=0:y=0" source_watermark/watermark.mp4
echo "水印添加完成"

}

#调用函数
watermark $1 $2
