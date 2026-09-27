# Graph Report - /home/xdranel/Code/Belajar/kampus/TugasAkhir  (2026-09-25)

## Corpus Check
- Corpus is ~5,761 words - fits in a single context window. You may not need a graph.

## Summary
- 17 nodes · 35 edges · 3 communities
- Extraction: 97% EXTRACTED · 3% INFERRED · 0% AMBIGUOUS · INFERRED: 1 edges (avg confidence: 0.75)
- Token cost: 0 input · 0 output

## Community Hubs (Navigation)
- Research Planning and Evidence
- PM2.5 Regression and Forecasting
- Air Quality Classification and Leakage

## God Nodes (most connected - your core abstractions)
1. `TA Preparation Pack: Air Quality PM2.5` - 11 edges
2. `Random Forest` - 8 edges
3. `PM2.5` - 7 edges
4. `PM2.5 Category Classification Design` - 5 edges
5. `PERBANDINGAN RANDOM FOREST DAN CATBOOST UNTUK KLASIFIKASI KUALITAS UDARA BERBASIS PM2.5` - 5 edges
6. `PM2.5 Air Quality Category` - 4 edges
7. `Pemodelan Prediksi Konsentrasi PM2.5 di DKI Jakarta menggunakan Random Forest berbasis Variabel Meteorologi dan Musim` - 4 edges
8. `Comparative Analysis of XGBoost, Random Forest, and Logistic Regression for Classifying Jakarta’s Air Pollution Index (ISPU)` - 4 edges
9. `Research Gap` - 3 edges
10. `PM2.5 Regression Design` - 3 edges

## Surprising Connections (you probably didn't know these)
- `TA Codex Handoff` --references--> `TA Preparation Pack: Air Quality PM2.5`  [EXTRACTED]
  prompt/TA_Codex_Handoff_Prompts.md → prompt/TA_Preparation_Pack_Air_Quality_PM25.md
- `TA Codex Handoff` --references--> `Master Prompt for Continuing TA Project`  [EXTRACTED]
  prompt/TA_Codex_Handoff_Prompts.md → prompt/TA_Preparation_Pack_Air_Quality_PM25.md

## Hyperedges (group relationships)
- **Alternative PM2.5 Research Designs** — prompt_ta_preparation_pack_air_quality_pm25_regression_design, prompt_ta_preparation_pack_air_quality_pm25_classification_design, prompt_ta_preparation_pack_air_quality_pm25_forecasting_design [EXTRACTED 1.00]
- **Literature to Dataset Research Decisions** — prompt_ta_preparation_pack_air_quality_pm25_literature_review, prompt_ta_preparation_pack_air_quality_pm25_research_gap, prompt_ta_preparation_pack_air_quality_pm25_dataset_strategy [EXTRACTED 1.00]

## Communities (3 total, 0 thin omitted)

### Community 0 - "Research Planning and Evidence"
Cohesion: 0.53
Nodes (6): TA Codex Handoff, Three-Year One-Region Dataset Strategy, Literature Review, Master Prompt for Continuing TA Project, TA Preparation Pack: Air Quality PM2.5, Research Gap

### Community 1 - "PM2.5 Regression and Forecasting"
Cohesion: 0.60
Nodes (6): Spatio-Temporal Dynamics of Extreme PM2.5 in Indonesia: A Weather-Based Hybrid Modeling Approach, Future PM2.5 or Category Forecasting Design, Pemodelan Prediksi Konsentrasi PM2.5 di DKI Jakarta menggunakan Random Forest berbasis Variabel Meteorologi dan Musim, PM2.5, Random Forest, PM2.5 Regression Design

### Community 2 - "Air Quality Classification and Leakage"
Cohesion: 0.70
Nodes (5): PM2.5 Category Classification Design, PERBANDINGAN RANDOM FOREST DAN CATBOOST UNTUK KLASIFIKASI KUALITAS UDARA BERBASIS PM2.5, PM2.5 Air Quality Category, Comparative Analysis of XGBoost, Random Forest, and Logistic Regression for Classifying Jakarta’s Air Pollution Index (ISPU), Same-Timestamp Target Leakage

## Suggested Questions
_Questions this graph is uniquely positioned to answer:_

- **Why does `TA Preparation Pack: Air Quality PM2.5` connect `Research Planning and Evidence` to `PM2.5 Regression and Forecasting`, `Air Quality Classification and Leakage`?**
  _High betweenness centrality (0.481) - this node is a cross-community bridge._
- **Why does `PM2.5` connect `PM2.5 Regression and Forecasting` to `Research Planning and Evidence`, `Air Quality Classification and Leakage`?**
  _High betweenness centrality (0.182) - this node is a cross-community bridge._
- **Why does `Random Forest` connect `PM2.5 Regression and Forecasting` to `Research Planning and Evidence`, `Air Quality Classification and Leakage`?**
  _High betweenness centrality (0.176) - this node is a cross-community bridge._