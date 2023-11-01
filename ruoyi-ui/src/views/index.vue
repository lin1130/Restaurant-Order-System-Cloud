<template>
  <div>
    <el-row>
      <el-col :span="12">
        <div class="res-name">
          SUBHAINI<br>
          FAMILY<br>
          RESTAURANT<br>
        </div>
        <div class="description">
          The family-owned restaurant offers a warm and welcoming atmosphere.
          With a diverse menu, it caters to various tastes,
          from mouthwatering homemade dishes to international flavors.
        </div>
      </el-col>
      <el-col :span="12">
        <el-card class="res-info">
          <div slot="header" class="clearfix">
            <span style="font-size: 30px;">Basic Infomation</span>
          </div>
          <div style="font-size: 20px">
            <p><b>Address:</b> Blk 721 Clementi West Street 2, Singapore 120000</p>
            <p><b>Contact:</b> +65 00000000</p>
            <p><b>Business Hours:</b> 11:00 - 23:00 everyday</p>
            <p><b>Average Cost:</b> $20-30 per person</p>
          </div>
        </el-card>
        <el-button type="primary" size="medium" @click="startOrdering"> start ordering </el-button>
      </el-col>
    </el-row>
  </div>
</template>
<script>
export default {
  components: {},
  props: [],
  data() {
    return {
      rules: {},
    }
  },
  computed: {},
  watch: {},
  created() {},
  mounted() {},
  methods: {
    startOrdering() {
      this.$router.push('item/item');
    }
  }
}

</script>
<style>
.res-name {
  font-size: 90px;
  margin-top: 20px;
  margin-left: 20px;
  color: #003366;
}
.description {
  margin-left: 20px;
  margin-top: 10px;
  margin-right: 50px;
  font-size: 20px;
  text-align: justify;
}
.clearfix:before,
.clearfix:after {
  display: table;
  content: "";
}
.el-card {
  margin-top: 40px;
  margin-right: 30px;
}
b {
  color: red;
}
.el-button {
  margin-top: 10px;
}
</style>
