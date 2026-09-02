import openai

# 1. INISIALISASI (Gunakan API Key kamu di sini)
# [cite: 12, 13]
api_key = ""
client = openai.OpenAI(
    base_url="https://openrouter.ai/api/v1",
    api_key=api_key,
)

# 2. PERSIAPAN PROMPT
# [cite: 15]
teks_prompt = "Jelaskan dalam 2 kalimat: Apa fungsi mekanisme Attention pada arsitektur Transformer?"
print(">>> Mengirim teks ke model...")

# 3. PEMANGGILAN API
# [cite: 18, 19, 20]
response = client.chat.completions.create(
    model="arcee-ai/trinity-large-preview:free",
    messages=[{"role": "user", "content": teks_prompt}],
)

# 4. EKSTRAKSI HASIL DAN TOKEN
# [cite: 22, 23, 24, 25]
jawaban_model = response.choices[0].message.content
token_input = response.usage.prompt_tokens
token_output = response.usage.completion_tokens
total_token = response.usage.total_tokens

# 5. MODIFIKASI: HITUNG ESTIMASI BIAYA
# [cite: 32, 33]
HARGA_INPUT_PER_1M = 1.25
HARGA_OUTPUT_PER_1M = 5.00
KURS_USD_TO_IDR = 16000

# Rumus: (jumlah_token / 1.000.000) * harga [cite: 34, 35]
biaya_input_usd = (token_input / 1000000) * HARGA_INPUT_PER_1M
biaya_output_usd = (token_output / 1000000) * HARGA_OUTPUT_PER_1M
total_biaya_usd = biaya_input_usd + biaya_output_usd  # [cite: 36]

total_biaya_idr = total_biaya_usd * KURS_USD_TO_IDR

# 6. TAMPILKAN HASIL (FORMAT RAPI)
# [cite: 37, 38]
print("-" * 50)
print(f"Jawaban Model:\n{jawaban_model}")
print("-" * 50)
print(f"Rincian Token:")
print(f"- Token Input   : {token_input}")
print(f"- Token Output  : {token_output}")
print(f"- Total Token   : {total_token}")
print("-" * 50)
print(f"Estimasi Biaya:")
print(f"- Biaya Input  : ${biaya_input_usd:.6f}")
print(f"- Biaya Output : ${biaya_output_usd:.6f}")
print(f"- Total (USD)  : ${total_biaya_usd:.6f}")
print(f"- Total (IDR)  : Rp {total_biaya_idr:,.2f}")
print("-" * 50)
