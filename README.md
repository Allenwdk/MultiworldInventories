# Multiworld Inventories

一个适用于 Minecraft 1.21.11 Fabric 的服务端模组，为每个世界提供独立的玩家物品栏。

> 本项目反编译自 Modrinth 上的 [multiworld-inventories](https://modrinth.com/mod/multiworld-inventories)，并修复了原版中通过 TP 传送到其他维度时会导致玩家背包丢失的 Bug。

## 简介

Multiworld Inventories 设计为 [Multiworld](https://modrinth.com/mod/multiworld) 的附属模组，允许玩家在不同世界/维度之间拥有完全独立的物品栏。

## 使用方法

当玩家通过传送门或指令传送到不同维度时，其物品栏（包括盔甲栏）会自动与原维度分离并切换到目标维度的物品栏。

## 功能

- **世界隔离物品栏**：玩家在不同维度/世界之间切换时，物品栏和盔甲会自动保存和加载。
- **下界与末地共享**：默认情况下，下界和末地与主世界共享同一套物品栏（可在配置中关闭）。
- **基于 Cardinal Components API**：使用 CCA 持久化存储每个玩家的物品栏数据。

## 依赖

| 依赖 | 版本 |
|------|------|
| Minecraft | 1.21.11 |
| Fabric Loader | >= 0.16.14 |
| Fabric API | 0.140.2+1.21.11 |
| Cardinal Components API | 7.3.0 |
| Multiworld | * |
| Java | >= 21 |

## 构建

```bash
./gradlew build
```

构建产物位于 `build/libs/MultiworldInventories-0.1.1.jar`。

## 配置

首次启动后会在 `config/multiworld-inventories/config.yml` 生成默认配置文件：

```yaml
# Multiworld Inventories Mod Config
netherSharesInventory: true   # 下界是否与主世界共享物品栏
theendSharesInventory: true   # 末地是否与主世界共享物品栏
```

- `netherSharesInventory` — 是否将下界物品栏与主世界合并。
- `theendSharesInventory` — 是否将末地物品栏与主世界合并。

将对应项设为 `false` 即可让该维度使用独立的物品栏。

## 待办事项

- [ ] 分离生命值与饥饿值
- [ ] 分离末影箱内容
- [ ] 分离经验值
- [ ] 支持自定义世界分组

## 协议

CC0-1.0
