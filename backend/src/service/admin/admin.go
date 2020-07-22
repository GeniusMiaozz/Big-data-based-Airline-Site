package admin

import (
	_ "github.com/GoAdminGroup/go-admin/adapter/iris"

	"github.com/GoAdminGroup/go-admin/engine"
	"github.com/GoAdminGroup/go-admin/modules/config"
	_ "github.com/GoAdminGroup/go-admin/modules/db/drivers/mysql"
	_ "github.com/GoAdminGroup/themes/adminlte"
	"github.com/kataras/iris/v12"
)

func Init(app *iris.Application) {
	eng := engine.Default()
	cfg := config.ReadFromJson("../config/goadmin.json")
	eng.AddConfig(cfg).Use(app)
}
