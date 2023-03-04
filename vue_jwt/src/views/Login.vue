<template>
  <div class="login-container">
    <el-row class="login-box">
      <el-form ref="loginRuleForm" :model="user" :rules="loginRules" label-width="65px" class="form">
        <el-form-item label="用户名" prop="username">
          <el-input type="text" v-model="user.username"></el-input>
        </el-form-item>
        <el-form-item label="密码" prop="password">
          <el-input type="password" v-model="user.password"></el-input>
        </el-form-item>
        <el-row class="button">
          <el-button type="primary" @click="onSubmit('loginRuleForm')">登录</el-button>
          <el-button @click="resetForm('loginRuleForm')">重置</el-button>
        </el-row>
      </el-form>
    </el-row>
  </div>
</template>

<script>
import {login} from "../network/user"

export default {
  name: "Login",
  data() {
    return {
      user: {
        username: '',
        password: ''
      },
      loginRules: {
        username: [
          {required: true, message: '请输入用户名', trigger: 'blur'},
          {min: 6, max: 15, message: '长度在 6 到 15 个字符', trigger: 'blur'}
        ],
        password: [
          {required: true, message: '请输入密码', trigger: 'blur'},
          {min: 6, max: 15, message: '长度在 6 到 15 个字符', trigger: 'blur'}
        ]
      }
    }
  },
  methods: {
    onSubmit(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          login(this.user).then(res => {
            if (res.code === 200) {
              console.log(res)
              this.$message.success('登录成功')
              localStorage.setItem('token', res.token)
              localStorage.setItem('user', res.data)
              this.$router.push('/user')
            }
          })
        } else {
          console.log('error submit!!')
          return false;
        }
      });
    },
    resetForm(formName) {
     this.$refs[formName].resetFields()
    }
  }
}
</script>

<style scoped>
  .login-container {
    display: flex;
    width: 400px;
    height: 100%;
    justify-content: center;
    align-items: center;
    margin: 0 auto;
  }

  .login-box {
    width: 100%;
    height: 30%;
    display: flex;
    justify-content: center;
    align-items: center;
    background-color: #fff;
    border-radius: 3px;
  }

  .form {
    width: 80%;
  }

  .button {
    display: flex;
    justify-content: space-evenly;
  }
</style>
