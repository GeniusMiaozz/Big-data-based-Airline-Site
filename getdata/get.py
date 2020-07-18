import random
import time

import requests
import csv
#防止反爬，首先会识别你的ip地址，如果你用同一个ip短时间内大量访问你的网址，它检测到就会封你的ip
#代理ip爬取
ip_list=[
    {"http": "1.197.203.225:9999"},
    {"http": "1.197.203.225:9999"},
    {"http": "1.197.203.225:9999"},
    {"http": "1.197.203.225:9999"},
]
proxy1 = random.choice(ip_list)


#构建代理处理器对象

#https://you.ctrip.com/sight/beijing1/229.html开发者工具，刷新
url = 'https://m.ctrip.com/restapi/soa2/13444/json/getCommentCollapseList?_fxpcqlniredt=09031138211157503391'

#headers是请求头
headers = {
        'user-agent': 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/53\
            7.36 (KHTML, like Gecko) Chrome/75.0.3770.142 Safari/537.36'
    }

#playroad是网页里面的数据
playroad = {
    'arg':{'channelType': '2','collapseType': '0','commentTagId': '0','pageIndex': '3','pageSize': '10','poiId': '24650593','sortType': '1','sourceType': '1','starType': '0'},
    'head':{'auth': "",'cid': "09031138211157503391",'ctok': "",'cver': "1.0",'extension': [],'lang': "01",'sid': "8888",'syscode': "09",'xsid': ""}
}

response = requests.post(url=url,headers=headers,json=playroad,proxies=proxy1 )
json_data = response.json()
#print(json_data)
data_list = json_data['result']['items']
for data in data_list:
    comment = data['content']
    score = data['score']
    print(comment,score)
    with open('lypl.csv', 'a', newline='') as csvfile:
        csvwriter = csv.writer(csvfile, delimiter=',')
        csvwriter.writerow([score,comment])
time.sleep(0.2)