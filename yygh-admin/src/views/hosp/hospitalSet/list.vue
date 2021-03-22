<template>
  <div class="app-container">
    <!-- banner列表 -->
    <el-table :data="list" stripe style="width: 100%">
      <el-table-column type="index" width="50" />
      <el-table-column prop="hosname" label="医院名称" />
      <el-table-column prop="hoscode" label="医院编号" />
      <el-table-column prop="apiUrl" label="api基础路径" width="200" />
      <el-table-column prop="contactsName" label="联系人姓名" />
      <el-table-column prop="contactsPhone" label="联系人手机" />
      <el-table-column label="状态" width="80">
        <!-- <template slot-scope="scope">{{ scope.row.status === 1 ? '可用' : '不可用' }}
</template> -->
      </el-table-column>
    </el-table>
  </div>
</template>
<script>
import hospitalSetApi from "@/api/hosp/hospitalSet";

export default {
  // 定义数据模型
  data() {
    return {
      list: null, // 列表
    };
  },

  // 页面渲染成功后获取数据
  created() {
    this.fetchData();
  },

  methods: {
    // 加载列表数据
    fetchData(page = 1) {
      console.log("翻页。。。" + page);
      // 异步获取远程数据（ajax）
      this.page = page;

      hospitalSetApi.getPageList(1, 10, null).then((response) => {
        this.list = response.data.records;
      });
    },
  },
};
</script>

