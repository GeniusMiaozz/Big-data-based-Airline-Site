package main

import (
	"log"
	"strconv"

	"github.com/kataras/iris/v12"

	"Airline/data"
	"Airline/service/airline"
	"Airline/service/identity"
	"Airline/utils"
)

/*
注册，成功时返回200状态码
POST方法，从表单中获取参数
*/
func SignUp(ctx iris.Context) {
	telephone, err := strconv.ParseInt(ctx.PostValue("telephone"), 10, 64)
	if err != nil {
		ctx.StatusCode(400)
		return
	}
	password := ctx.PostValue("password")
	status, err := identity.SignUp(
		&data.Member_Register{
			Telephone: telephone,
			Password:  password,
		})
	if err != nil {
		if status == 1 {
			ctx.StatusCode(500)
			log.Println(err.Error())
		} else {
			ctx.StatusCode(400)
			ctx.JSON(iris.Map{
				"err_msg": err.Error(),
			})
		}
	}
}

/*
登录，成功时返回200状态码和token
POST方法，从表单中获取参数
使用手机号可以登录
*/
func SignIn(ctx iris.Context) {
	telephone, err := strconv.ParseInt(ctx.PostValue("telephone"), 10, 64)
	if err != nil {
		ctx.StatusCode(400)
		return
	}
	password := ctx.PostValue("password")
	status, member_no, err := identity.SignIn(
		&data.Member_Register{
			Telephone: telephone,
			Password:  password,
		})
	if err != nil {
		if status == 1 {
			ctx.StatusCode(500)
			log.Println(err.Error())
		} else {
			ctx.StatusCode(400)
			ctx.JSON(iris.Map{
				"err_msg": err.Error(),
			})
		}
	}
	//成功登录，提供一个token
	token, err := utils.GenerateJwtToken(member_no, ctx.RemoteAddr())
	if err != nil {
		ctx.StatusCode(500)
		log.Println(err.Error())
	}
	ctx.JSON(iris.Map{
		"token": token,
	})
}

/*
登出，成功时返回200状态码，注销token
GET方法，从URL查询片段中获得参数
*/
func SignOut(ctx iris.Context) {

}

/*
重置密码，成功时返回200状态码
POST方法，从表单中获取参数
*/
func ResetPassword(ctx iris.Context) {

}

/*
查询航班，成功返回200
GET方法
*/
func SearchForFlight(ctx iris.Context) {
	_, fql, err := airline.SearchForFlight(
		&data.Flight_Query{
			Dep_Ct: ctx.URLParam("city_from"),
			Arr_Ct: ctx.URLParam("city_to"),
			Date:   ctx.URLParam("date_start"),
		})
	if err != nil {
		ctx.StatusCode(500)
		log.Println(err)
	}
	ctx.JSON(iris.Map{
		"flights": fql,
	})
}
