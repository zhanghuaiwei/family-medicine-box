<template>
  <div class="inventory-container">
    <el-card>
      <template #header>
        <div class="card-header">
          <span>库存监控</span>
          <el-button type="primary" @click="handleRefresh">刷新数据</el-button>
        </div>
      </template>

      <div class="stats-grid">
        <el-card class="stat-card">
          <div class="stat-item">
            <div class="stat-icon">
              <el-icon class="icon"><i-ep-box /></el-icon>
            </div>
            <div class="stat-content">
              <div class="stat-value">{{ totalItems }}</div>
              <div class="stat-label">总库存</div>
            </div>
          </div>
        </el-card>

        <el-card class="stat-card warning">
          <div class="stat-item">
            <div class="stat-icon">
              <el-icon class="icon"><i-ep-warning /></el-icon>
            </div>
            <div class="stat-content">
              <div class="stat-value">{{ lowStockItems }}</div>
              <div class="stat-label">低库存</div>
            </div>
          </div>
        </el-card>

        <el-card class="stat-card danger">
          <div class="stat-item">
            <div class="stat-icon">
              <el-icon class="icon"><i-ep-warning-filled /></el-icon>
            </div>
            <div class="stat-content">
              <div class="stat-value">{{ expiringItems }}</div>
              <div class="stat-label">即将过期</div>
            </div>
          </div>
        </el-card>

        <el-card class="stat-card critical">
          <div class="stat-item">
            <div class="stat-icon">
              <el-icon class="icon"><i-ep-error /></el-icon>
            </div>
            <div class="stat-content">
              <div class="stat-value">{{ expiredItems }}</div>
              <div class="stat-label">已过期</div>
            </div>
          </div>
        </el-card>
      </div>

      <div class="chart-section">
        <el-card>
          <template #header>
            <div class="chart-header">
              <span>库存趋势</span>
              <el-select
                v-model="chartPeriod"
                @change="handlePeriodChange"
                style="width: 120px"
              >
                <el-option label="最近7天" value="7" />
                <el-option label="最近30天" value="30" />
                <el-option label="最近90天" value="90" />
              </el-select>
            </div>
          </template>
          <div class="chart-container">
            <div ref="chartRef" style="height: 400px"></div>
          </div>
        </el-card>
      </div>

      <div class="inventory-list">
        <el-card>
          <template #header>
            <div class="list-header">
              <span>库存列表</span>
              <el-input
                v-model="searchKeyword"
                placeholder="搜索药品名称"
                style="width: 300px"
                prefix-icon="i-ep-search"
                @input="handleSearch"
              />
            </div>
          </template>
          <el-table :data="filteredInventory" style="width: 100%" border>
            <el-table-column prop="medicineName" label="药品名称" width="180" />
            <el-table-column prop="category" label="分类" width="120" />
            <el-table-column
              prop="currentQuantity"
              label="当前库存"
              width="100"
            />
            <el-table-column prop="minStock" label="最低库存" width="100" />
            <el-table-column prop="unit" label="单位" width="80" />
            <el-table-column prop="expiryDate" label="过期日期" width="150" />
            <el-table-column prop="location" label="存放位置" width="120" />
            <el-table-column prop="status" label="状态" width="100">
              <template #default="scope">
                <el-tag :type="getTagType(scope.row.status)">
                  {{ scope.row.status }}
                </el-tag>
              </template>
            </el-table-column>
            <el-table-column label="操作" width="150">
              <template #default="scope">
                <el-button
                  type="primary"
                  size="small"
                  @click="handleEdit(scope.row)"
                >
                  编辑
                </el-button>
                <el-button
                  type="danger"
                  size="small"
                  @click="handleDelete(scope.row.id)"
                >
                  删除
                </el-button>
              </template>
            </el-table-column>
          </el-table>

          <div class="pagination-container">
            <el-pagination
              @size-change="handleSizeChange"
              @current-change="handleCurrentChange"
              :current-page="currentPage"
              :page-sizes="[10, 20, 50, 100]"
              :page-size="pageSize"
              layout="total, sizes, prev, pager, next, jumper"
              :total="total"
            />
          </div>
        </el-card>
      </div>
    </el-card>

    <!-- 编辑库存对话框 -->
    <el-dialog :title="dialogTitle" v-model="dialogVisible" width="500px">
      <el-form :model="form" label-width="100px">
        <el-form-item label="药品名称">
          <el-input v-model="form.medicineName" placeholder="请输入药品名称" />
        </el-form-item>
        <el-form-item label="当前库存">
          <el-input-number
            v-model="form.currentQuantity"
            :min="0"
            placeholder="请输入当前库存"
          />
        </el-form-item>
        <el-form-item label="最低库存">
          <el-input-number
            v-model="form.minStock"
            :min="1"
            placeholder="请输入最低库存"
          />
        </el-form-item>
        <el-form-item label="单位">
          <el-input v-model="form.unit" placeholder="请输入单位" />
        </el-form-item>
        <el-form-item label="过期日期">
          <el-date-picker
            v-model="form.expiryDate"
            type="date"
            placeholder="选择过期日期"
            style="width: 100%"
          />
        </el-form-item>
        <el-form-item label="存放位置">
          <el-input v-model="form.location" placeholder="请输入存放位置" />
        </el-form-item>
      </el-form>
      <template #footer>
        <span class="dialog-footer">
          <el-button @click="dialogVisible = false">取消</el-button>
          <el-button type="primary" @click="handleSave">保存</el-button>
        </span>
      </template>
    </el-dialog>
  </div>
</template>

<script setup lang="ts">
import { ref, computed, reactive, onMounted, onUnmounted } from "vue";
import * as echarts from "echarts";
import {
  iEpBox,
  iEpWarning,
  iEpWarningFilled,
  iEpError,
  iEpSearch,
} from "@element-plus/icons-vue";

// 统计数据
const totalItems = ref(128);
const lowStockItems = ref(5);
const expiringItems = ref(3);
const expiredItems = ref(1);

// 图表数据
const chartPeriod = ref("7");
const chartOption = ref({
  tooltip: {
    trigger: "axis",
  },
  legend: {
    data: ["库存总量", "低库存数量"],
  },
  xAxis: {
    type: "category",
    data: ["1月", "2月", "3月", "4月", "5月", "6月", "7月"],
  },
  yAxis: {
    type: "value",
  },
  series: [
    {
      data: [100, 110, 115, 120, 125, 128, 128],
      type: "line",
      name: "库存总量",
    },
    {
      data: [2, 3, 4, 4, 5, 5, 5],
      type: "line",
      name: "低库存数量",
    },
  ],
});

// 库存列表
const inventory = ref([
  {
    id: "1",
    medicineName: "阿莫西林胶囊",
    category: "消炎药",
    currentQuantity: 5,
    minStock: 10,
    unit: "盒",
    expiryDate: "2026-12-31",
    location: "药箱上层",
    status: "低库存",
  },
  {
    id: "2",
    medicineName: "布洛芬片",
    category: "退烧药",
    currentQuantity: 15,
    minStock: 5,
    unit: "盒",
    expiryDate: "2026-06-30",
    location: "药箱中层",
    status: "正常",
  },
  {
    id: "3",
    medicineName: "感冒清热颗粒",
    category: "感冒药",
    currentQuantity: 8,
    minStock: 5,
    unit: "盒",
    expiryDate: "2026-03-15",
    location: "药箱下层",
    status: "即将过期",
  },
  {
    id: "4",
    medicineName: "盐酸伐地那非片",
    category: "心血管药",
    currentQuantity: 2,
    minStock: 5,
    unit: "盒",
    expiryDate: "2025-12-31",
    location: "药箱中层",
    status: "已过期",
  },
]);

// 搜索和分页
const searchKeyword = ref("");
const currentPage = ref(1);
const pageSize = ref(10);
const total = ref(inventory.value.length);

// 对话框参数
const dialogVisible = ref(false);
const dialogTitle = ref("编辑库存");
const form = reactive({
  id: "",
  medicineName: "",
  currentQuantity: 0,
  minStock: 0,
  unit: "",
  expiryDate: "",
  location: "",
});

// 图表相关
const chartRef = ref(null);
let chart = null;

// 初始化图表
onMounted(() => {
  if (chartRef.value) {
    chart = echarts.init(chartRef.value);
    chart.setOption(chartOption.value);

    // 监听窗口大小变化
    window.addEventListener("resize", handleResize);
  }
});

// 销毁图表
onUnmounted(() => {
  if (chart) {
    chart.dispose();
    chart = null;
  }
  window.removeEventListener("resize", handleResize);
});

// 处理窗口大小变化
const handleResize = () => {
  if (chart) {
    chart.resize();
  }
};

// 处理周期变更
const handlePeriodChange = () => {
  // 实际项目中这里会根据选择的周期加载不同的数据
  console.log("周期变更", chartPeriod.value);
  // 模拟数据更新
  if (chart) {
    chart.setOption(chartOption.value);
  }
};

// 过滤后的库存列表
const filteredInventory = computed(() => {
  if (!searchKeyword.value) {
    return inventory.value;
  }
  return inventory.value.filter((item) =>
    item.medicineName.toLowerCase().includes(searchKeyword.value.toLowerCase()),
  );
});

// 处理搜索
const handleSearch = () => {
  currentPage.value = 1;
};

// 处理分页
const handleSizeChange = (size: number) => {
  pageSize.value = size;
};

const handleCurrentChange = (current: number) => {
  currentPage.value = current;
};

// 处理刷新
const handleRefresh = () => {
  // 实际项目中这里会调用后端API刷新数据
  console.log("刷新数据");
};

// 获取标签类型
const getTagType = (status: string) => {
  switch (status) {
    case "低库存":
      return "warning";
    case "即将过期":
      return "danger";
    case "已过期":
      return "danger";
    default:
      return "success";
  }
};

// 处理编辑
const handleEdit = (row: any) => {
  dialogTitle.value = "编辑库存";
  Object.assign(form, row);
  dialogVisible.value = true;
};

// 处理删除
const handleDelete = (id: string) => {
  // 实际项目中这里会调用后端API
  console.log("删除库存", id);
  // 模拟删除
  const index = inventory.value.findIndex((item) => item.id === id);
  if (index > -1) {
    inventory.value.splice(index, 1);
    total.value = inventory.value.length;
  }
};

// 处理保存
const handleSave = () => {
  // 实际项目中这里会调用后端API
  console.log("保存库存", form);
  // 模拟保存
  if (form.id) {
    // 编辑
    const index = inventory.value.findIndex((item) => item.id === form.id);
    if (index > -1) {
      inventory.value[index] = { ...form };
    }
  } else {
    // 添加
    const newItem = {
      ...form,
      id: Date.now().toString(),
      status: form.currentQuantity <= form.minStock ? "低库存" : "正常",
    };
    inventory.value.unshift(newItem);
    total.value = inventory.value.length;
  }
  dialogVisible.value = false;
};
</script>

<style scoped lang="less">
.inventory-container {
  padding: 20px;
}

.card-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  font-size: 16px;
  font-weight: bold;
}

.stats-grid {
  display: grid;
  grid-template-columns: repeat(auto-fit, minmax(200px, 1fr));
  gap: 20px;
  margin-bottom: 30px;
}

.stat-card {
  border-left: 4px solid #409eff;

  &.warning {
    border-left-color: #e6a23c;
  }

  &.danger {
    border-left-color: #f56c6c;
  }

  &.critical {
    border-left-color: #909399;
  }
}

.stat-item {
  display: flex;
  align-items: center;

  .stat-icon {
    margin-right: 20px;
    .icon {
      font-size: 48px;
      color: #409eff;
    }
  }

  .stat-content {
    .stat-value {
      font-size: 32px;
      font-weight: bold;
      color: #303133;
      margin-bottom: 5px;
    }

    .stat-label {
      font-size: 14px;
      color: #606266;
    }
  }
}

.chart-section {
  margin-bottom: 30px;
}

.chart-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  font-size: 16px;
  font-weight: bold;
}

.chart-container {
  margin-top: 20px;
}

.inventory-list {
  margin-top: 30px;
}

.list-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  font-size: 16px;
  font-weight: bold;
}

.pagination-container {
  margin-top: 20px;
  display: flex;
  justify-content: flex-end;
}

.dialog-footer {
  text-align: right;
}
</style>
