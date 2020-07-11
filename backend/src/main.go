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
	app.Use(cors)

	if _, err := r_db.Connect(); err != nil {
		log.Println(err.Error())
		log.Fatal("Failed to connect database.")
	}
	defer func() {
		r_db.Disconnect()
	}()

	//route
	app.Post("/signup", SignUp)
	app.Post("/signin", SignIn)
	app.Get("/searchforflight", SearchForFlight)

	//listen and serve
	app.Run(iris.Addr(":8080"))
}

func cors(ctx iris.Context) {
	ctx.Header("Access-Control-Allow-Origin", "*")
	if ctx.Request().Method == "OPTIONS" {
		ctx.Header("Access-Control-Allow-Methods",
			"GET,POST,PUT,DELETE,PATCH,OPTIONS")
		ctx.Header("Access-Control-Allow-Headers",
			"Content-Type,Accept,Authorization")
		ctx.StatusCode(204)
		return
	}
	ctx.Next()
}
