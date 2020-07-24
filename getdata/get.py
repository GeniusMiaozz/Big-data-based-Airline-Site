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

temps=[
    '10558614','10524171','44775645','89388','90682','75898','10558672','10758351','90706','10558935',
    '92371','83524','77593','97847','97241','10558642','41399624','10558672','77595','87289',
    '75595','75596','76625','75597','13378901','10758716','13382815','89343','83199','92189',
    '75682','81938','10557626','75681','75684','30231156','33786065','10522927','10538278','86008',
    '76342','76447','88132','82204','75906','75903','78616','75936','78547','75938',
    '76865','75630','79168','30791706','82556','31077253','33978279','10558683','10535175','24650593'
        ]
for k in range(len(temps)):
    playroad = {
        'arg':{'channelType': '2','collapseType': '0','commentTagId': '0','pageIndex': '1','pageSize': '10','poiId': temps[k],'sortType': '1','sourceType': '1','starType': '0'},
        'head':{'auth': "",'cid':"09031138211157503391",'ctok': "",'cver': "1.0",'extension': [],'lang': "01",'sid': "8888",'syscode': "09",'xsid': ""}
    }
    response = requests.post(url=url,headers=headers,json=playroad,proxies=proxy1 )
    json_data = response.json()
    #print(json_data)
    data_list = json_data['result']['items']
    for data in data_list:
        comment = data['content']
        comment = comment.replace('&#x20','')
        comment = comment.replace('\xa0','')
        comment = comment.replace('\U0001f61e','')
        comment = comment.replace('\u26f0','')
        comment = comment.replace('\ufe0f','')
        comment = comment.replace('\U0001f339','')
        comment = comment.replace('\U0001f609','')
        comment = comment.replace('\U0001f44d','')
        comment = comment.replace('\U0001f62d','')
        comment = comment.replace('\U0001f3a0','')
        comment = comment.replace('\U0001f4a7','')
        comment = comment.replace('\U0001f525','')
        comment = comment.replace('\u0e53','')
        comment = comment.replace('\xb4','')
        comment = comment.replace('\u2661','')
        comment = comment.replace('\U0001f440','')
        comment = comment.replace('\u20e3','')
        comment = comment.replace('\U0001f697','')
        comment = comment.replace('\u272a','')
        comment = comment.replace('\U0001f236','')
        comment = comment.replace('\U0001f193','')
        comment = comment.replace('\U0001f3c4','')
        comment = comment.replace('\u2764','')
        comment = comment.replace('\U0001f618','')
        comment = comment.replace('\U0001f37a','')
        comment = comment.replace('\u2795','')
        comment = comment.replace('\U0001f61b','')
        comment = comment.replace('\U0001f630','')
        comment = comment.replace('\U0001f3fb','')
        comment = comment.replace('\U0001f4af','')
        comment = comment.replace('\U0001f604','')
        comment = comment.replace('\U0001f621','')
        comment = comment.replace('\U0001f463','')
        comment = comment.replace('\U0001f973','')
        comment = comment.replace('\u2b55','')
        comment = comment.replace('\U0001f607','')
        comment = comment.replace('\U0001f602','')
        comment = comment.replace('\u24ff','')
        score = data['score']
        print(comment,score)
        with open('lypl.csv', 'a', newline='') as csvfile:
            csvwriter = csv.writer(csvfile, delimiter=',')
            csvwriter.writerow([score,comment])
    time.sleep(0.2)
    
    playroad = {
        'arg':{'channelType': '2','collapseType': '0','commentTagId': '0','pageIndex': '2','pageSize': '10','poiId': temps[k],'sortType': '1','sourceType': '1','starType': '0'},
        'head':{'auth': "",'cid':"09031138211157503391",'ctok': "",'cver': "1.0",'extension': [],'lang': "01",'sid': "8888",'syscode': "09",'xsid': ""}
    }
    response = requests.post(url=url,headers=headers,json=playroad,proxies=proxy1 )
    json_data = response.json()
    #print(json_data)
    data_list = json_data['result']['items']
    for data in data_list:
        comment = data['content']
        comment = comment.replace('&#x20','')
        comment = comment.replace('\xa0','')
        comment = comment.replace('\U0001f339','')
        comment = comment.replace('\U0001f61e','')
        comment = comment.replace('\u26f0','')
        comment = comment.replace('\ufe0f','')
        comment = comment.replace('\U0001f609','')
        comment = comment.replace('\U0001f44d','')
        comment = comment.replace('\U0001f62d','')
        comment = comment.replace('\U0001f3a0','')
        comment = comment.replace('\U0001f4a7','')
        comment = comment.replace('\U0001f525','')
        comment = comment.replace('\u0e53','')
        comment = comment.replace('\xb4','')
        comment = comment.replace('\u2661','')
        comment = comment.replace('\U0001f440','')
        comment = comment.replace('\u20e3','')
        comment = comment.replace('\U0001f697','')
        comment = comment.replace('\u272a','')
        comment = comment.replace('\U0001f236','')
        comment = comment.replace('\U0001f193','')
        comment = comment.replace('\U0001f3c4','')
        comment = comment.replace('\u2764','')
        comment = comment.replace('\U0001f618','')
        comment = comment.replace('\U0001f37a','')
        comment = comment.replace('\u2795','')
        comment = comment.replace('\U0001f61b','')
        comment = comment.replace('\U0001f630','')
        comment = comment.replace('\U0001f3fb','')
        comment = comment.replace('\U0001f4af','')
        comment = comment.replace('\U0001f604','')
        comment = comment.replace('\U0001f621','')
        comment = comment.replace('\U0001f463','')
        comment = comment.replace('\U0001f973','')
        comment = comment.replace('\u2b55','')
        comment = comment.replace('\U0001f607','')
        comment = comment.replace('\U0001f602','')
        comment = comment.replace('\u24ff','')
        score = data['score']
        print(comment,score)
        with open('lypl.csv', 'a', newline='') as csvfile:
            csvwriter = csv.writer(csvfile, delimiter=',')
            csvwriter.writerow([score,comment])
    time.sleep(0.2)
    
    playroad = {
        'arg':{'channelType': '2','collapseType': '0','commentTagId': '0','pageIndex': '3','pageSize': '10','poiId': temps[k],'sortType': '1','sourceType': '1','starType': '0'},
        'head':{'auth': "",'cid':"09031138211157503391",'ctok': "",'cver': "1.0",'extension': [],'lang': "01",'sid': "8888",'syscode': "09",'xsid': ""}
    }
    response = requests.post(url=url,headers=headers,json=playroad,proxies=proxy1 )
    json_data = response.json()
    #print(json_data)
    data_list = json_data['result']['items']
    for data in data_list:
        comment = data['content']
        comment = comment.replace('&#x20','')
        comment = comment.replace('\xa0','')
        comment = comment.replace('\U0001f339','')
        comment = comment.replace('\U0001f61e','')
        comment = comment.replace('\u26f0','')
        comment = comment.replace('\ufe0f','')
        comment = comment.replace('\U0001f609','')
        comment = comment.replace('\U0001f44d','')
        comment = comment.replace('\U0001f62d','')
        comment = comment.replace('\U0001f3a0','')
        comment = comment.replace('\U0001f4a7','')
        comment = comment.replace('\U0001f525','')
        comment = comment.replace('\u0e53','')
        comment = comment.replace('\xb4','')
        comment = comment.replace('\u2661','')
        comment = comment.replace('\U0001f440','')
        comment = comment.replace('\u20e3','')
        comment = comment.replace('\U0001f697','')
        comment = comment.replace('\u272a','')
        comment = comment.replace('\U0001f236','')
        comment = comment.replace('\U0001f193','')
        comment = comment.replace('\U0001f3c4','')
        comment = comment.replace('\u2764','')
        comment = comment.replace('\U0001f618','')
        comment = comment.replace('\U0001f37a','')
        comment = comment.replace('\u2795','')
        comment = comment.replace('\U0001f61b','')
        comment = comment.replace('\U0001f630','')
        comment = comment.replace('\U0001f3fb','')
        comment = comment.replace('\U0001f4af','')
        comment = comment.replace('\U0001f604','')
        comment = comment.replace('\U0001f621','')
        comment = comment.replace('\U0001f463','')
        comment = comment.replace('\U0001f973','')
        comment = comment.replace('\u2b55','')
        comment = comment.replace('\U0001f607','')
        comment = comment.replace('\U0001f602','')
        comment = comment.replace('\u24ff','')
        score = data['score']
        print(comment,score)
        with open('lypl.csv', 'a', newline='') as csvfile:
            csvwriter = csv.writer(csvfile, delimiter=',')
            csvwriter.writerow([score,comment])
    time.sleep(0.2)