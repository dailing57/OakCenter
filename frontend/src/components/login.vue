<template>
  <el-form ref="form" :model="form" size="normal" :rules="rules" style="padding: 0 5%  3%  5% ">
    <el-form-item prop="id" >
      <el-input  prefix-icon="el-icon-user-solid" v-model="form.id" placeholder="请输入账号"></el-input>
    </el-form-item>
    <el-form-item prop="pwd">
      <el-input prefix-icon="el-icon-lock" v-model="form.pwd" show-password placeholder="请输入密码"></el-input>
    </el-form-item>
    <el-form-item>
      <div style="display: flex;justify-content: center;">
        <el-input prefix-icon="el-icon-key" v-model="form.validCode" style="width: 50%; margin-right: 5%" placeholder="请输入验证码"></el-input>
        <div class="validCode">
          <ValidCode @input="createValidCode" />
        </div>
      </div>
    </el-form-item>
    <el-form-item>
      <el-button style="width: 100%" type="primary" @click="login">登 录</el-button>
    </el-form-item>
  </el-form>
</template>

<script>
import request from "@/utils/request";
import ValidCode from "@/components/ValidCode";

export default {
  name: "Login",
  components: {
    ValidCode,
  },
  data() {
    return {
      form: {role: 'common'},
      rules: {
        username: [
          {required: true, message: '请输入用户名', trigger: 'blur'},
        ],
        password: [
          {required: true, message: '请输入密码', trigger: 'blur'},
        ],
      },
      validCode: '',
    }
  },
  created() {
    sessionStorage.removeItem("user")
  },
  methods: {
    createValidCode(data) {
      this.validCode = data
    },
    login() {
      this.$refs['form'].validate((valid) => {
        if (valid) {
          if (!this.form.validCode) {
            this.$message.error("请填写验证码")
            return
          }
          if(this.form.validCode.toLowerCase() !== this.validCode.toLowerCase()) {
            this.$message.error("验证码错误")
            return
          }
          request.post("/login", this.form).then(res => {
            if (res.code === '0') {
              this.$message({
                type: "success",
                message: "登录成功"
              })
              this.$emit('success',false)
              sessionStorage.setItem("user", JSON.stringify(res.data))  // 缓存用户信息
            } else {
              this.$message({
                type: "error",
                message: res.msg
              })
            }
          })
        }
      })
    },
  },
}
</script>

<style scoped>

</style>
