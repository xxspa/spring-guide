<script setup lang="ts"></script>

<template>
  <div class="app">
    <!-- 导航栏示例 -->
    <nav>
      <router-link to="/">首页</router-link>
      <router-link to="/about">关于</router-link>
      <router-link to="/user/123">用户123</router-link>
    </nav>

    <!-- 这里是路由匹配到的组件显示的地方 -->
    <router-view></router-view>
  </div>
</template>

<style scoped>
.logo {
  height: 6em;
  padding: 1.5em;
  will-change: filter;
  transition: filter 300ms;
}

.logo:hover {
  filter: drop-shadow(0 0 2em #646cffaa);
}

.logo.vue:hover {
  filter: drop-shadow(0 0 2em #42b883aa);
}

/* 简单样式 */
.router-link-active {
  color: #42b983;
  font-weight: bold;
}
</style>
