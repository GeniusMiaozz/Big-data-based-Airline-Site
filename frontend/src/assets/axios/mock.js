import Mock from 'mockjs'

Mock.mock('/login_x', 'post', function (options) {
    console.log(options)
    return {
        token: "this_is_token",
        status: 200
    }
})

Mock.mock('/register_x','post',function (options) {
    console.log(options)
    return{
        token: "this_is_token_r",
        status: 200
    }
})

Mock.mock('/cheeps','get',function (options) {
    console.log(options)
    return{

    }

})
