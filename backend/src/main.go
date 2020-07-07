package main

import (
	"github.com/kataras/iris/v12"
	"github.com/kataras/iris/v12/middleware/logger"
	//"github.com/kataras/iris/v12/middleware/recover"
)

func main() {
	//construct the app
	app := iris.New()
	//config middlewares
	app.Use(logger.New())
	//app.Use(recover.New())

	//route

	//listen and serve
	app.Run(iris.Addr(":8080"))
}
