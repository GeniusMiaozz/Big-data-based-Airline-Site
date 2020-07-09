package utils

import (
	"log"

	"github.com/bwmarrin/snowflake"
)

var snode *snowflake.Node

func init() {
	var err error
	snode, err = snowflake.NewNode(0)
	if err != nil {
		log.Fatal("Failed to construct snowflake generator.")
	}
}

func GenerateId() int64 {
	return snode.Generate().Int64()
}
