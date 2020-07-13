package recommend

import (
	"Airline/data"
	"Airline/data/r_db"
	"fmt"
	"strconv"
	"time"
)

//热门景点的推荐
func SiteRecommend(id string)(int,[]data.Site,error){
	//输入：用户id
	//输出：根据点击量和目的地进行，输出推荐给该用户的所有景点
	//包含所有的景点，在每次进行数据处理后更新表，点击量排序，本函数就是返回推荐给该用户的多条数据库查询结果
	db, _ := r_db.Connect()
	rows, err:=db.Query([]string{"*"}, []string{"site_rec"},"userid="+id)
	defer rows.Close()
	if err != nil {
		return 1,nil, err
	}
	var sl []data.Site
	for rows.Next() {
		var s data.Site
		rows.Scan(&s.Id,&s.UserId,&s.ATTRACTION,&s.CLICK_COUNT,&s.LOCATION,&s.AVG_PRICE)
		//fmt.Println(s.Id)
		//fmt.Println(s.UserId)
		//fmt.Println(s.ATTRACTION)
		//fmt.Println(s.CLICK_COUNT)
		//fmt.Println(s.LOCATION)
		//fmt.Println(s.AVG_PRICE)
		sl = append(sl, s)
	}
	return 0,sl,err
}

//推荐相关的酒店住宿,0-正常，1-内部错误
func RestRecommend(id string)(int,[]data.Hotel,error){
	//输入：userid
	//输出：推荐的酒店记录
	db, _ := r_db.Connect()
	fmt.Println(id)
	rows, err:=db.Query([]string{"*"}, []string{"hotel_rec"},"userid="+id)
	//defer rows.Close()
	if err != nil {
		return 1,nil, err
	}
	var sl []data.Hotel
	for rows.Next() {
		var s data.Hotel
		rows.Scan(&s.Id,&s.HOTEL,&s.LOCATION,&s.CLASS,&s.AVG_PRICE,&s.COUNTER,&s.Userid)
		//fmt.Println(s.HOTEL)
		//fmt.Println(s.LOCATION)
		//fmt.Println(s.CLASS)
		//fmt.Println(s.AVG_PRICE)
		//fmt.Println(s.COUNTER)
		//fmt.Println(s.Userid)
		sl = append(sl, s)
	}
	return 0,sl,err
}

//低价机票推荐,0-正常，1-内部错误
func TicketRecommend(id string)(int,[]data.Ticket,error){
	//根据id的身份进行区分
	//id=0游客采用用户相似互推地点，id=1往返常客采用平吕统计方式推送频率高的地点
	//输入：userid
	//输出：查询到的低价机票的集合
	db, _ := r_db.Connect()
	fmt.Println(id)
	rows, err:=db.Query([]string{"*"}, []string{"flight_recommendation_table"},"userid="+id)
	defer rows.Close()
	if err != nil {
		return 1,nil, err
	}
	var sl []data.Ticket
	for rows.Next() {
		var s data.Ticket
		rows.Scan(&s.Id,&s.Userid,&s.FLIGHT_NO,&s.Price,&s.Date,&s.DEP_CT,&s.ARR_CT,&s.TYPE)
		fmt.Println(s.FLIGHT_NO)
		sl = append(sl, s)
	}
	return 0,sl,err

}

//根据当前季节推荐不同的旅游产品，套餐，活动
func TravelRecommend()(int,[]data.Travel,error){
	//t:=time.Now()获取当前时间，2020-07-12 13:55:21.3410012 +0800 CST m=+0.006015601
	t := time.Now().String()
	//fmt.Println(t[5:7])
	mm, _ :=strconv.ParseInt(t[5:7],10,64)
	//fmt.Println(mm)
	db, _ := r_db.Connect()
	rows, err:=db.Query([]string{"*"},
						[]string{"travel_recommendation_table"},
						"MONTH="+strconv.FormatInt(mm,10)+" or "+
		                "MONTH="+strconv.FormatInt(mm+1,10)+" or "+
		                "MONTH="+strconv.FormatInt(mm+2,10)+" or "+
						"MONTH="+strconv.FormatInt(mm+3,10)+" or "+
						"MONTH="+strconv.FormatInt(mm+4,10))
	if err != nil {
		return 1,nil, err
	}
	var sl []data.Travel

	for rows.Next() {
		var s data.Travel
		rows.Scan(&s.Id,&s.MENU,&s.GUIDE,&s.TYPE,&s.MONTH)
		fmt.Println(s.MENU)
		sl = append(sl, s)
	}
	return 0, nil, nil
}

//积分商品的推荐，可能不做了
func goodsRecommend()(){//根据用户积分情况，自动匹配最相关的积分兑换产品，按先后排序
	//输入：用户id
	//访问数据库找到该用户id的积分，商城产品中积分相近的产品降序排列
	//输出：区间内产品积分降序排列后的集合
}

//基于目的地的推荐，已有方法，备用方案
//根据使用者的不同身份进行区分
func destRecommand(){
	//输入：用户id
	//id=0是游客，用主成分分析法对目的地热门程度进行评价后排序或者是对已有数据用户目的地进行聚类分析目的地命中次数排序后，进行推荐
	//id!=0是用户，对用户的目的地进行统计分析，先进行身份的预测，背包客->，差旅人->推荐目的地相关的酒店住宿
	//输出：推荐的目的地id，同时在数据库中查找对应目的地的热门套餐，进行显示和返回
}
//景点推荐，按用户评论情况打分，推荐好评去处，有多余时间做