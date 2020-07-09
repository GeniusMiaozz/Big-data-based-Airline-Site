package main

import (
	"log"
	"strconv"

	"github.com/kataras/iris/v12"

	"Airline/data"
	"Airline/service/identity"
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
	status, err := identity.SignIn(
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
描述登出逻辑，成功时返回200状态码，注销token
GET方法，从URL查询片段中获得参数
*/
func SignOut(ctx iris.Context) {

}

/*
描述重置密码逻辑，成功时返回200状态码
POST方法，从表单中获取参数
*/
func ResetPassword(ctx iris.Context) {

}
