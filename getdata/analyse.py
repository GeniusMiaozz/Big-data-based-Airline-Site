
import snownlp
from snownlp import SnowNLP
yuyi = []
f = open('lypl.txt')
data_list = f.readlines()
for data in data_list:
    s = SnowNLP(data)
    # 返回0-1的值，越接近1越开心
    a = s.sentiments
    print(a)
    print(data)
    yuyi.append(a)

c = []
'''
a1 = 0
for i in range(30):
    a1 += yuyi[i]
b1 = a1/30
c.append(b1)
a2 = 0
for i in range(30,60):
    a2 += yuyi[i]
b2 = a2/30
c.append(b2)
a3 = 0
for i in range(60,90):
    a3 += yuyi[i]
b3 = a3/30
c.append(b3)
d3 = 0
for i in range(90,120):
    d3 += yuyi[i]
c3 = d3/30
c.append(c3)
a4 = 0
for i in range(120,150):
    a4 += yuyi[i]
b4 = a4/30
c.append(b4)
d4 = 0
for i in range(150,180):
    d4 += yuyi[i]
c4 = d4/30
c.append(c4)
a5 = 0
for i in range(180,210):
    a5 += yuyi[i]
b5 = a5/30
c.append(b5)
a6 = 0
for i in range(210,240):
    a6 += yuyi[i]
b6 = a6/30
c.append(b6)
a7 = 0
for i in range(240,270):
    a7 += yuyi[i]
b7 = a7/30
c.append(b7)
d7 = 0
for i in range(270,300):
    d7+=yuyi[i]
c7 = d7/30
c.append(c7)
a8 = 0
for i in range(300,330):
    a8 += yuyi[i]
b8 = a8/30
c.append(b8)
a9 = 0
for i in range(330,360):
    a9 += yuyi[i]
b9 = a9/30
c.append(b9)
a10 = 0
for i in range(360,390):
    a10 += yuyi[i]
b10 = a10/30
c.append(b10)
a11 = 0
for i in range(390,420):
    a11 += yuyi[i]
b11 = a11/30
c.append(b11)
a12 = 0
for i in range(420,450):
    a12 += yuyi[i]
b12 = a12/30
c.append(b12)
a13 = 0
for i in range(450,480):
    a13 += yuyi[i]
b13 = a13/30
c.append(b13)
a14 = 0
for i in range(480,510):
    a14 += yuyi[i]
b14 = a14/30
c.append(b14)
a15 = 0
for i in range(510,540):
    a15 += yuyi[i]
b15 = a15/30
c.append(b15)
a16 = 0
for i in range(540,570):
    a16 += yuyi[i]
b16 = a16/30
c.append(b16)
a17 = 0
for i in range(570,600):
    a17 += yuyi[i]
b17 = a17/30
c.append(b17)
a18 = 0
for i in range(600,630):
    a18 += yuyi[i]
b18 = a18/30
c.append(b18)
a19 = 0
for i in range(630,660):
    a19 += yuyi[i]
b19 = a19/30
c.append(b19)
a20 = 0
for i in range(660,690):
    a20 += yuyi[i]
b20 = a20/30
c.append(b20)
a21 = 0
for i in range(690,720):
    a21 += yuyi[i]
b21 = a21/30
c.append(b21)
a22 = 0
for i in range(720,750):
    a22 += yuyi[i]
b22 = a22/30
c.append(b22)
a23 = 0
for i in range(750,780):
    a23 += yuyi[i]
b23 = a23/30
c.append(b23)
a24 = 0
for i in range(780,810):
    a24 += yuyi[i]
b24 = a24/30
c.append(b24)
a25 = 0
for i in range(810,840):
    a25 += yuyi[i]
b25 = a25/30
c.append(b25)
a26 = 0
for i in range(840,870):
    a26 += yuyi[i]
b26 = a26/30
c.append(b26)
a27 = 0
for i in range(870,900):
    a27 += yuyi[i]
b27 = a27/30
c.append(b27)
a28 = 0
for i in range(900,930):
    a28 += yuyi[i]
b28 = a28/30
c.append(b28)
a29 = 0
for i in range(930,960):
    a29 += yuyi[i]
b29 = a29/30
c.append(b29)
a30 = 0
for i in range(960,990):
    a30 += yuyi[i]
b30 = a30/30
c.append(b30)
a31 = 0
for i in range(990,1020):
    a31 += yuyi[i]
b31 = a31/30
c.append(b31)
a32 = 0
for i in range(1020,1050):
    a32 += yuyi[i]
b32 = a32/30
c.append(b32)
a33 = 0
for i in range(1050,1080):
    a33 += yuyi[i]
b33 = a33/30
c.append(b33)
a34 = 0
for i in range(1080,1110):
    a34 += yuyi[i]
b34 = a34/30
c.append(b34)
a35 = 0
for i in range(1110,1140):
    a35 += yuyi[i]
b35 = a35/30
c.append(b35)
a36 = 0
for i in range(1140,1170):
    a36 += yuyi[i]
b36 = a36/30
c.append(b36)
a37 = 0
for i in range(1170,1200):
    a37 += yuyi[i]
b37 = a37/30
c.append(b37)
a38 = 0
for i in range(1200,1230):
    a38 += yuyi[i]
b38 = a38/30
c.append(b38)
a39 = 0
for i in range(1230,1260):
    a39 += yuyi[i]
b39 = a39/30
c.append(b39)
a40 = 0
for i in range(1260,1290):
    a40 += yuyi[i]
b40 = a40/30
c.append(b40)
a41 = 0
for i in range(1290,1320):
    a41 += yuyi[i]
b41 = a41/30
c.append(b41)
c41 = 0
for i in range(1320,1350):
    c41 += yuyi[i]
d41 = c41/30
c.append(d41)
a42 = 0
for i in range(1350,1380):
    a42 += yuyi[i]
b42 = a42/30
c.append(b42)
a43 = 0
for i in range(1380,1410):
    a43 += yuyi[i]
b43= a43/30
c.append(b43)
a44 = 0
for i in range(1410,1440):
    a44 += yuyi[i]
b44 = a44/30
c.append(b44)
a45 = 0
for i in range(1440,1470):
    a45 += yuyi[i]
b45 = a45/30
c.append(b45)
a46 = 0
for i in range(1470,1500):
    a46 += yuyi[i]
b46 = a46/30
c.append(b46)
a47 = 0
for i in range(1500,1530):
    a47 += yuyi[i]
b47 = a47/30
c.append(b47)
a48 = 0
for i in range(1530,1560):
    a48 += yuyi[i]
b48 = a48/30
c.append(b48)
a49 = 0
for i in range(1560,1590):
    a49 += yuyi[i]
b49 = a49/30
c.append(b49)
a50 = 0
for i in range(1590,1620):
    a50 += yuyi[i]
b50 = a50/30
c.append(b50)
a51 = 0
for i in range(1620,1650):
    a51 += yuyi[i]
b51 = a51/30
c.append(b51)
a52 = 0
for i in range(1650,1680):
    a52 += yuyi[i]
b52 = a52/30
c.append(b52)
a53 = 0
for i in range(1680,1710):
    a53 += yuyi[i]
b53 = a53/30
c.append(b53)
a54 = 0
for i in range(1710,1740):
    a54 += yuyi[i]
b54 = a54/30
c.append(b54)
a55 = 0
for i in range(1740,1770):
    a55 += yuyi[i]
b55 = a55/30
c.append(b55)
a56 = 0
for i in range(1770,1800):
    a56 += yuyi[i]
b56 = a56/30
c.append(b56)
a57 = 0
for i in range(1800,1830):
    a57 += yuyi[i]
b57 = a57/30
c.append(b57)
a58 = 0
for i in range(1830,1860):
    a58 += yuyi[i]
b58 = a58/30
c.append(b58)
a59 = 0
for i in range(1860,1890):
    a59 += yuyi[i]
b59 = a59/30
c.append(b59)
a60 = 0
for i in range(1890,1920):
    a60 += yuyi[i]
b60 = a60/30
c.append(b60)
a61 = 0
for i in range(1920,1950):
    a61 += yuyi[i]
b61 = a61/30
c.append(b61)
a62 = 0
for i in range(1950,1980):
    a62 += yuyi[i]
b62 = a62/30
c.append(b62)
a63 = 0
for i in range(1980,2010):
    a63 += yuyi[i]
b63 = a63/30
c.append(b63)
a64 = 0
for i in range(2010,2040):
    a64 += yuyi[i]
b64 = a64/30
c.append(b64)
a65 = 0
for i in range(2040,2070):
    a65 += yuyi[i]
b65 = a65/30
c.append(b65)
a66 = 0
for i in range(2070,2100):
    a66 += yuyi[i]
b66 = a66/30
c.append(b66)



print(c)
print(len(c))
'''
'''
华东
0.8782473503663317    4       上海迪士尼乐园
0.933722536502602     1       上海科技馆
0.7040858662232554    10      上海海昌海洋公园
0.8396982994534206    7       东方明珠
0.9112930099410149    3       杭州西湖
0.8593444873935215    6       宋城
0.7481008524748393    8       灵隐寺
0.7316875251119463    9       厦门鼓浪屿
0.9124766541567636    2       厦门方特公园
0.8679792157988537    5       苏州周庄
华南
0.7528950748886716   8        三亚    蜈支洲岛
0.8650186328954398   3        天涯海角
0.8257445184285997   6        三亚亚特兰蒂斯水世界
0.9055344387084098   1        亚龙湾热带天堂森林公园
0.6261041883316535   10       象山景区
0.8265880309603083   5        桂林 漓江 
0.7926499975281484   7        龙脊梯田
0.6423862120924537   9        广州 长隆水上乐园
0.8600133146270195   4        广州塔
0.894470687923085    2        长隆欢乐世界
华中
0.8986134775829119   4        武汉 武汉欢乐谷
0.8383607518061366   7        武汉东湖海底世界
0.8573700226122373   6        黄鹤楼
0.9130221671373114   2        武汉海昌极地海洋公园
0.8226307512726023   8        郑州方特欢乐世界
0.6951445905385416   10       少林寺
0.8941498823509738   5        银基冰雪世界
0.8056309409268252   9        长沙世界之窗
0.9462371849899388   1        湖南省博物馆
0.911438384004269    3        长沙海底世界
华北
0.833422912704478   7         北京故宫
0.8485109201319915  5         八达岭长城
0.8062498252144076  9         圆明园
0.8756348504172441  3         颐和园
0.90912732084668    2         古北水镇
0.845914777016784   6         天津欢乐谷
0.8290783778034133  8         天津方特
0.6944618491593968  10        天津航母公园
0.9137087457818969  1         山西晋祠公园
0.8638856240886096  4         山西乐山大佛
西北
0.6716326270551128  9         西安兵马俑
0.8291445878485171  5         大唐芙蓉园
0.9786126932980357  1         大唐不夜城
0.7819345453289522  7         华清宫
0.9151527630358586  3         陕西历史博物馆
0.7442492920506012  8         兰州极地海洋世界
0.6225376756248042  10        西部恐龙水乐园
0.8288549209690231  6         乌鲁木齐齐天大峡谷
0.8689773802349158  4         南山牧场
0.9668361814134402  2         乌鲁木齐植物园
西南
0.8374295585103471  6         成都大熊猫繁育基地
0.9198141315117122  1         都江堰
0.8160471735060818  7         成都欢乐谷
0.918287725136679   2         昆明石林风景区
0.8429793502084089  5         云南民族村
0.9164830353375247  3         滇池
0.7954241046947599  9         九乡
0.7807509341094605  10        布达拉宫
0.8584588555313567  4         纳木错
0.8058847711565907  8         大昭寺
东北
0.9302052709412574  1         大连星海广场
0.7491604031921851  8         老虎滩海洋公园
0.8804586951165873  3         大连圣亚海洋世界
0.6807145651647509  10        哈尔滨融创乐园
0.8465738334376802  5         哈尔滨极地馆
0.8475694461723631  4         波塞冬旅游度假区
0.7848106492396998  7         普罗旺斯薰衣草庄园
0.9203351009426536  2         长春净月潭 
0.7477065554770815  9         东北虎园
0.8088277039284424  6         吉林省博物馆


'''