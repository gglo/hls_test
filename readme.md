# hls测试仓库

### ffmpeg切片command
ffmpeg -re -i  ~/zoom/hls_test/source_3/GMT20220622-012948_XMNUP-test_1760x900.mp4 -codec copy -map 0 -f segment -segment_list playlist.m3u8 -segment_list_flags +live -segment_time 5 out%03d.mkv

### 索引文件地址
https://raw.githubusercontent.com/gglo/hls_test/main/source_1/playlist.m3u8


### 片段地址
https://rgumjigt5.bkt.clouddn.com/hls/20220819/out000.mkv