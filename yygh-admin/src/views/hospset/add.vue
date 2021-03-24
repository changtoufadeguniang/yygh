<template>
  <div class="app-container">
    医院设置添加
    <el-form label-width="120px">
      <el-form-item label="医院名称">
        <el-input v-model="hospitalSet.hosname" />
      </el-form-item>

      <el-form-item label="医院编号">
        <el-input v-model="hospitalSet.hoscode" />
      </el-form-item>

      <el-form-item label="api基础路径">
        <el-input v-model="hospitalSet.apiUrl" />
      </el-form-item>

      <el-form-item label="联系人姓名">
        <el-input v-model="hospitalSet.contactsName" />
      </el-form-item>
      <el-form-item label="联系人手机">
        <el-input v-model="hospitalSet.contactsPhone" />
      </el-form-item>

      <el-form-item>
        <el-button type="primary" @click="saveOrUpdate">保存</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>
<script>
import hospset from '@/api/hosp/hospitalSet'
export default {
  data() {
      return {
         hospitalSet:{} 
      }
   },
   created(){
     //页面渲染之前
     if(this.$route.params && this.$route.params.id){
       //表示路由上有id是修改
       const id = this.$route.params.id
        this.getHostSet(id)
      } else {
         //表单数据清空
         this.hospitalSet = {}
      }
   },
   methods:{
     getHostSet(id){
         hospset.getHospSet(id).then(response=>{
           this.hospitalSet=response.data;
         })
     },
     //添加
      save() {
         hospset.saveHospSet(this.hospitalSet)
            .then(response => {
               //提示
               this.$message({
                  type: 'success',
                  message: '添加成功!'
               })
               //跳转列表页面，使用路由跳转方式实现
               this.$router.push({path:'/hospSet/list'})
            })
      },
      //修改
      update() {
         hospset.updateHospSet(this.hospitalSet)
            .then(response => {
               //提示
               this.$message({
                  type: 'success',
                  message: '修改成功!'
               })
               //跳转列表页面，使用路由跳转方式实现
               this.$router.push({path:'/hospSet/list'})
            })
      },
      saveOrUpdate() {
         //判断添加还是修改
         if(!this.hospitalSet.id) { //没有id，做添加
            this.save();
         }else {//修改
            this.update()
         }
      }
     
   }
};
</script>

<style>
</style>