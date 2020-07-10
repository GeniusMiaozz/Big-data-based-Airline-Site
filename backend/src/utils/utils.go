package utils

import (
	"log"

	"github.com/bwmarrin/snowflake"
	jwt_go "github.com/dgrijalva/jwt-go"
	"github.com/iris-contrib/middleware/jwt"

	"Airline/config"
)

var (
	snode            *snowflake.Node
	JwtAuthenticator *jwt.Middleware
	jwtSigningMethod jwt_go.SigningMethod
)

func init() {
	//snowflake
	var err error
	snode, err = snowflake.NewNode(0)
	if err != nil {
		log.Fatal("Failed to construct snowflake generator.")
	}

	//jwt
	switch config.A_system_config.Jwt.Method {
	case "ES256":
		jwtSigningMethod = jwt.SigningMethodES256
	case "ES384":
		jwtSigningMethod = jwt.SigningMethodES384
	case "ES512":
		jwtSigningMethod = jwt.SigningMethodES512
	case "HS256":
		jwtSigningMethod = jwt.SigningMethodHS256
	case "HS384":
		jwtSigningMethod = jwt.SigningMethodHS384
	case "HS512":
		jwtSigningMethod = jwt.SigningMethodHS512
	}

	JwtAuthenticator = jwt.New(jwt.Config{
		// Extract by "token" url parameter.
		Extractor: jwt.FromParameter("token"),
		ValidationKeyGetter: func(token *jwt.Token) (interface{}, error) {
			return []byte(config.A_system_config.Jwt.Secret), nil
		},
		SigningMethod: jwtSigningMethod,
	})
}

func GenerateSnowFlakeId() int64 {
	return snode.Generate().Int64()
}

func GenerateJwtToken(member_no string, ip string) (string, error) {
	log.Println(ip)
	token := jwt.NewTokenWithClaims(
		jwtSigningMethod,
		jwt.MapClaims{
			"member_no": member_no,
			"ip":        ip,
		})
	tokenString, err := token.SignedString([]byte(config.A_system_config.Jwt.Secret))
	if err != nil {
		return "", err
	}
	return tokenString, nil
}
