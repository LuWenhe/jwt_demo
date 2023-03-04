<template>
  <div class="user-container">
    <el-row class="show-box">
      <span>Hello, {{val}}</span>
      <el-button type="primary" @click="logout()">退出</el-button>
    </el-row>
  </div>
</template>

<script>
export default {
  name: "User",
  data() {
    return {
      val: ""
    }
  },
  methods: {
    logout() {
      let token = localStorage.getItem('token')

      if (token !== null && token !== '') {
        localStorage.removeItem('token')
        localStorage.removeItem('user')
        this.$message('退出登录成功')
        this.$router.push('/login')
      }
    },
  },
  created() {
    this.val = localStorage.getItem('user')
  }
}
</script>

<style scoped>
  .user-container {
    display: flex;
    width: 400px;
    height: 100%;
    justify-content: center;
    align-items: center;
    margin: 0 auto;
  }

  .show-box {
    width: 100%;
    height: 30%;
    display: flex;
    justify-content: center;
    align-items: center;
    background-color: #fff;
    border-radius: 3px;
  }

  .show-box span {
    font-size: 30px;
  }
</style>
