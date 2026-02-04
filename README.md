# order-service-lab

## 📋 簡介

初步雛型化，採用 **Maven 多模組架構**，實作 **RabbitMQ 非同步事件驅動機制**，將訂單建立請求解耦為 Producer（order-api）與 Consumer（order-worker），降低寫入請求對 DB 的瞬間高峰風險。Redis 緩存與 DB 持久化邏輯待實作

## 🎯 專案目標

模擬透過 RabbitMQ 將訂單 request 透過 MQ 事件驅動、非同步消費機制，加上 Redis 緩存資料，降低對 DB 瞬間高峰查詢打爆 DB 的風險。

## 🏗️ 技術架構

### 架構設計
- **事件驅動架構（EDA）**：採用 RabbitMQ 作為訊息中介層
- **非同步處理**：訂單建立請求透過 MQ 非同步消費，避免同步阻塞
- **服務解耦**：API 服務與 Worker 服務分離，獨立部署與擴展

### 模組說明

#### 1. **order-api** (Producer)
- 提供 RESTful API 接收訂單建立請求
- 將訂單訊息轉換為 MQ 訊息格式
- 透過 RabbitMQ 發送訂單事件

#### 2. **order-worker** (Consumer)
- 監聽 RabbitMQ 訂單佇列
- 非同步消費訂單訊息
- 處理訂單邏輯（目前為記錄 log，DB 寫入待實作）

#### 3. **common** (共用模組)
- 共用訊息模型（`OrderMessage`）
- RabbitMQ 配置屬性（`RabbitOrderProperties`）



### 待實作 ⚠️
- ⚠️ **Redis 緩存**：僅有配置與依賴（`spring-boot-starter-data-redis`），尚未實作實際快取邏輯
- ⚠️ **DB 寫入**：Consumer 目前僅記錄 log，尚未實作實際寫入邏輯（MongoDB）
- ⚠️ **錯誤處理**：尚未實作死信佇列（DLQ）與錯誤處理機制

## 🛠️ 技術棧

- **Java**: 17
- **Spring Boot**: 3.2.5
- **Maven**: 多模組專案
- **RabbitMQ**: 3-management（訊息佇列）
- **Redis**: 7（緩存，待實作）
- **MongoDB**: （資料庫，待實作）
- **Lombok**: 1.18.32
- **MapStruct**: 1.5.5.Final（DTO 轉換）


## 🔄 事件流程

```
1. Client 發送訂單請求
   ↓
2. OrderController 接收請求
   ↓
3. OrderService 處理業務邏輯（生成訂單 ID、時間戳）
   ↓
4. OrderProducer 將訂單訊息發送到 RabbitMQ
   ↓
5. RabbitMQ Exchange → Queue（路由）
   ↓
6. OrderConsumer 監聽並消費訊息
   ↓
7. 處理訂單邏輯（目前為 log，待實作 DB 寫入）
```

## 📌 附註

- 部分功能尚未完整實作
- Redis 緩存功能僅有基礎配置，實際快取邏輯待開發
- Consumer 的 DB 寫入邏輯待實作
- 生產環境使用需補充錯誤處理、監控、日誌等機制



