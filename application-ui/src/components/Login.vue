<template>
  <div class="login-container">
    <el-form :model="loginForm" label-width="80px" class="login-box">
      <h3>系统登录</h3>
      <el-form-item label="用户名">
        <el-input v-model="loginForm.username"></el-input>
      </el-form-item>
      <el-form-item label="密码">
        <el-input type="password" v-model="loginForm.password"></el-input>
      </el-form-item>
      <el-button type="primary" @click="handleLogin">登录</el-button>
    </el-form>
  </div>
</template>

<script setup lang="ts">
import {reactive} from 'vue'
import axios from 'axios'
import {ElMessage} from 'element-plus'

const loginForm = reactive({
  username: '',
  password: ''
})

const handleLogin = () => {
  // 注意：Spring Security 默认接收表单格式（x-www-form-urlencoded）
  const params = new URLSearchParams()
  params.append('username', loginForm.username)
  params.append('password', loginForm.password)

  axios.post('/api/login', params).then(res => {
    if (res.data.status === 'success') {
      ElMessage.success('欢迎回来！')
      // 跳转逻辑：router.push('/dashboard')
    }
  }).catch(() => {
    ElMessage.error('登录失败，请检查账号密码')
  })
}
</script>

<style scoped>
.login-box {
  width: 350px;
  margin: 180px auto;
  border: 1px solid #ddd;
  padding: 35px;
  border-radius: 8px;
}
</style>