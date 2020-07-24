package main

import (
	"log"

	"github.com/kataras/iris/v12"
	"github.com/kataras/iris/v12/middleware/logger"

	//"github.com/kataras/iris/v12/middleware/recover"

	_ "Airline/config"
	"Airline/data/r_db"
	_ "Airline/utils"
)

func main() {
	//construct the app
	app := iris.New()
	//config middlewares
	app.Use(logger.New())
	//app.Use(recover.New())

	if _, err := r_db.Connect(); err != nil {
		log.Println(err.Error())
		log.Fatal("Failed to connect database.")
	}
	defer func() {
		r_db.Disconnect()
	}()

	//route
	//app.Post("/signup", SignUp)
	//app.Post("/signin", SignIn)
	//app.Get("/index",restRecommend)
	//app.Get("/index",ticketRecommend)
	//app.Get("/index",siteRecommend)
	app.Get("/index",travelRecommend)
	//listen and serve
	app.Run(iris.Addr(":8080"))
}
