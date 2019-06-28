https://blog.csdn.net/babylove_BaLe/article/details/73201115
https://www.cnblogs.com/sebastian-tyd/p/7895182.html

https://www.updatestar.com/directdownload/tera-term/2037688
https://pan.baidu.com/s/1RWKvUxC9e_pP67speLtIcA

https://blog.csdn.net/liuhaiguang2012/article/details/79394971

https://gitee.com/loyin/ECP
https://gitee.com/erzhongxmu/jeewms/tree/4e14482d14e211001075059865c97fb718bc4efc/

https://www.oschina.net/p/ruoyi

http://www.intra-mart.jp/e-learning/training/02_java_free_tera/index.html

http://www.tuniu.com/tucom/210462170

sqlplus /nolog
exp system/123456@172.16.192.42:1523/sdc_test02 file='C:/sdc_test02.dmp'

alter user system identified by 123456
create user sdc_test02 identified by sdc_test02;
grant connect, resource to sdc_test02;

imp system/123456@localhost:1521/sdcdb fromuser=system touser=system file='C:/sdc_test02.dmp' ignore=y

mstsc
114.116.83.237
