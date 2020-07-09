package config

import (
	"log"

	"github.com/BurntSushi/toml"
)

type system_config struct {
	RDatabase string `toml:"r_database"`
}

var (
	A_system_config system_config
)

func init() {
	LoadConfig("../config/system.toml", &A_system_config)
	log.Println(A_system_config)
}

func LoadConfig(path string, target interface{}) {
	_, err := toml.DecodeFile(path, target)
	if err != nil {
		log.Fatalln("Fail to load ", path, ".")
	}
}
