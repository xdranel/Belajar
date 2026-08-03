from __future__ import annotations

from html import escape
from pathlib import Path
from zipfile import ZIP_DEFLATED, ZipFile


OUT = Path("docs/EduGen_AI_Presentation_ID.pptx")
SLIDES = [
    (
        "EduGen AI",
        "Generate Complete Learning Materials using Open Source Generative AI",
        [
            "Aplikasi AI untuk membuat materi pembelajaran dari satu topik",
            "Menggunakan model open-source, bukan OpenAI/Gemini/Claude API",
            "Dibangun sebagai proyek akhir mata kuliah Generative AI",
        ],
    ),
    (
        "Latar Belakang Masalah",
        "Mahasiswa dan pengajar membutuhkan materi belajar yang cepat, lengkap, dan terstruktur.",
        [
            "Membuat rangkuman, kuis, flashcard, dan roadmap secara manual memakan waktu",
            "Materi sering tersebar dan tidak konsisten",
            "Banyak solusi AI memakai layanan closed-source yang tidak sesuai requirement proyek",
        ],
    ),
    (
        "Solusi: EduGen AI",
        "EduGen AI menghasilkan paket materi belajar lengkap dari satu input topik.",
        [
            "Learning summary, explanation, key concepts, analogy, examples, notes",
            "Flashcards, multiple choice quiz, essay questions, answer key",
            "Mini project, learning roadmap, dan references",
            "Export ke PDF, Markdown, TXT, dan HTML",
        ],
    ),
    (
        "Target Pengguna",
        "EduGen AI dirancang untuk berbagai kebutuhan belajar.",
        [
            "Mahasiswa dan siswa SMA",
            "Self learner",
            "Guru atau dosen",
            "Peserta sertifikasi",
            "Orang yang ingin membuat bahan ajar cepat",
        ],
    ),
    (
        "Fitur Utama",
        "Fitur inti sudah dipisahkan berdasarkan modul agar mudah diuji dan dikembangkan.",
        [
            "Generate materi pembelajaran",
            "Generate flashcard dan quiz",
            "Riwayat hasil generate dengan SQLite",
            "Download PDF, Markdown, TXT, HTML",
            "Settings model dan parameter generasi",
            "Evaluation dan data pipeline",
        ],
    ),
    (
        "Arsitektur Sistem",
        "EduGen AI menggunakan Python modular monolith.",
        [
            "UI: Streamlit multi-page app",
            "AI backend: prompt builder, tokenizer, model loader, inference manager",
            "Storage: SQLite repository",
            "Export: document exporter",
            "Data pipeline dan evaluation framework dipisah dari UI",
        ],
    ),
    (
        "Alur AI Pipeline",
        "Pipeline dibuat explainable dari input sampai output.",
        [
            "User input -> validation -> prompt formatting",
            "Prompt -> tokenizer -> local open-source model",
            "Generation -> response cleaning -> structured output",
            "Output disimpan ke history dan dapat diexport",
        ],
    ),
    (
        "Model Open-Source",
        "Default model: Qwen/Qwen2.5-0.5B-Instruct.",
        [
            "Dipilih karena kecil, instruction-tuned, dan kompatibel dengan Hugging Face Transformers",
            "Lisensi Apache-2.0",
            "Model dapat diganti melalui konfigurasi",
            "Tidak menggunakan API closed-source",
        ],
    ),
    (
        "Data Engineering Pipeline",
        "Pipeline data mendukung dataset publik dan reproducible.",
        [
            "Rekomendasi dataset: SciQ, Dolly 15k, OpenAssistant OASST1, OpenStax",
            "Validasi missing value, duplicate, panjang sample, dan encoding",
            "Cleaning, normalization, split 80/10/10",
            "Metadata, statistics, dan quality report",
        ],
    ),
    (
        "Evaluation Framework",
        "Evaluasi mencakup kualitas, performa, resource, dan human evaluation.",
        [
            "ROUGE, BLEU, BERTScore fallback",
            "Latency, output length, generation speed, memory dan CPU fields",
            "Human scoring: correctness, coherence, readability, educational value",
            "Error analysis: repetition, missing sections, output terlalu pendek/panjang",
        ],
    ),
    (
        "Demo Workflow",
        "Alur demo yang disarankan untuk presentasi.",
        [
            "Jalankan smoke check",
            "Buka Streamlit app",
            "Masukkan topik, difficulty, language, quiz count, dan parameter generation",
            "Generate materi, lihat section output, history, dan download",
            "Tunjukkan docs data pipeline dan evaluation report",
        ],
    ),
    (
        "Kelebihan, Batasan, dan Pengembangan",
        "EduGen AI sudah berbentuk produk AI lokal, tetapi masih punya ruang pengembangan.",
        [
            "Kelebihan: modular, explainable, reproducible, open-source model",
            "Batasan: model kecil dapat menghasilkan output kurang stabil",
            "Mitigasi: prompt ketat, temperature rendah, evaluation framework",
            "Future work: RAG, vector database, LMS integration, voice/image generation",
        ],
    ),
]


def para(text: str, size: int = 2200, bold: bool = False) -> str:
    return (
        "<a:p><a:r><a:rPr lang=\"id-ID\" sz=\"%d\"%s/>"
        "<a:t>%s</a:t></a:r></a:p>"
    ) % (size, " b=\"1\"" if bold else "", escape(text))


def text_box(shape_id: int, x: int, y: int, cx: int, cy: int, lines: list[str], size: int) -> str:
    paragraphs = "".join(para(line, size=size, bold=index == 0) for index, line in enumerate(lines))
    return f"""
    <p:sp>
      <p:nvSpPr><p:cNvPr id="{shape_id}" name="Text {shape_id}"/><p:cNvSpPr txBox="1"/><p:nvPr/></p:nvSpPr>
      <p:spPr><a:xfrm><a:off x="{x}" y="{y}"/><a:ext cx="{cx}" cy="{cy}"/></a:xfrm><a:prstGeom prst="rect"><a:avLst/></a:prstGeom><a:noFill/><a:ln><a:noFill/></a:ln></p:spPr>
      <p:txBody><a:bodyPr wrap="square"/><a:lstStyle/>{paragraphs}</p:txBody>
    </p:sp>"""


def slide_xml(index: int, title: str, subtitle: str, bullets: list[str]) -> str:
    bullet_lines = [f"- {bullet}" for bullet in bullets]
    return f"""<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<p:sld xmlns:a="http://schemas.openxmlformats.org/drawingml/2006/main"
       xmlns:r="http://schemas.openxmlformats.org/officeDocument/2006/relationships"
       xmlns:p="http://schemas.openxmlformats.org/presentationml/2006/main">
  <p:cSld>
    <p:bg><p:bgPr><a:solidFill><a:srgbClr val="F7FAFC"/></a:solidFill><a:effectLst/></p:bgPr></p:bg>
    <p:spTree>
      <p:nvGrpSpPr><p:cNvPr id="1" name=""/><p:cNvGrpSpPr/><p:nvPr/></p:nvGrpSpPr>
      <p:grpSpPr><a:xfrm><a:off x="0" y="0"/><a:ext cx="0" cy="0"/><a:chOff x="0" y="0"/><a:chExt cx="0" cy="0"/></a:xfrm></p:grpSpPr>
      {text_box(2, 540000, 420000, 8200000, 700000, [title], 3600)}
      {text_box(3, 540000, 1150000, 8200000, 700000, [subtitle], 2000)}
      {text_box(4, 820000, 2050000, 7600000, 3300000, bullet_lines, 1900)}
      {text_box(5, 540000, 6350000, 8200000, 260000, [f"EduGen AI | Slide {index}"], 1000)}
    </p:spTree>
  </p:cSld>
  <p:clrMapOvr><a:masterClrMapping/></p:clrMapOvr>
</p:sld>"""


def write_pptx(path: Path) -> None:
    path.parent.mkdir(parents=True, exist_ok=True)
    with ZipFile(path, "w", ZIP_DEFLATED) as z:
        z.writestr("[Content_Types].xml", content_types())
        z.writestr("_rels/.rels", root_rels())
        z.writestr("ppt/presentation.xml", presentation_xml())
        z.writestr("ppt/_rels/presentation.xml.rels", presentation_rels())
        z.writestr("ppt/theme/theme1.xml", theme_xml())
        z.writestr("ppt/slideMasters/slideMaster1.xml", slide_master_xml())
        z.writestr("ppt/slideMasters/_rels/slideMaster1.xml.rels", slide_master_rels())
        z.writestr("ppt/slideLayouts/slideLayout1.xml", slide_layout_xml())
        z.writestr("ppt/slideLayouts/_rels/slideLayout1.xml.rels", slide_layout_rels())
        for index, (title, subtitle, bullets) in enumerate(SLIDES, start=1):
            z.writestr(f"ppt/slides/slide{index}.xml", slide_xml(index, title, subtitle, bullets))
            z.writestr(f"ppt/slides/_rels/slide{index}.xml.rels", slide_rels())


def content_types() -> str:
    slide_overrides = "\n".join(
        f'<Override PartName="/ppt/slides/slide{i}.xml" ContentType="application/vnd.openxmlformats-officedocument.presentationml.slide+xml"/>'
        for i in range(1, len(SLIDES) + 1)
    )
    return f"""<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<Types xmlns="http://schemas.openxmlformats.org/package/2006/content-types">
  <Default Extension="rels" ContentType="application/vnd.openxmlformats-package.relationships+xml"/>
  <Default Extension="xml" ContentType="application/xml"/>
  <Override PartName="/ppt/presentation.xml" ContentType="application/vnd.openxmlformats-officedocument.presentationml.presentation.main+xml"/>
  <Override PartName="/ppt/slideMasters/slideMaster1.xml" ContentType="application/vnd.openxmlformats-officedocument.presentationml.slideMaster+xml"/>
  <Override PartName="/ppt/slideLayouts/slideLayout1.xml" ContentType="application/vnd.openxmlformats-officedocument.presentationml.slideLayout+xml"/>
  <Override PartName="/ppt/theme/theme1.xml" ContentType="application/vnd.openxmlformats-officedocument.theme+xml"/>
  {slide_overrides}
</Types>"""


def root_rels() -> str:
    return """<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<Relationships xmlns="http://schemas.openxmlformats.org/package/2006/relationships">
  <Relationship Id="rId1" Type="http://schemas.openxmlformats.org/officeDocument/2006/relationships/officeDocument" Target="ppt/presentation.xml"/>
</Relationships>"""


def presentation_xml() -> str:
    slide_ids = "\n".join(f'<p:sldId id="{255+i}" r:id="rId{i}"/>' for i in range(1, len(SLIDES) + 1))
    return f"""<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<p:presentation xmlns:a="http://schemas.openxmlformats.org/drawingml/2006/main"
                xmlns:r="http://schemas.openxmlformats.org/officeDocument/2006/relationships"
                xmlns:p="http://schemas.openxmlformats.org/presentationml/2006/main">
  <p:sldMasterIdLst><p:sldMasterId id="2147483648" r:id="rId{len(SLIDES)+1}"/></p:sldMasterIdLst>
  <p:sldIdLst>{slide_ids}</p:sldIdLst>
  <p:sldSz cx="9144000" cy="5143500" type="screen16x9"/>
  <p:notesSz cx="6858000" cy="9144000"/>
</p:presentation>"""


def presentation_rels() -> str:
    slide_rels_xml = "\n".join(
        f'<Relationship Id="rId{i}" Type="http://schemas.openxmlformats.org/officeDocument/2006/relationships/slide" Target="slides/slide{i}.xml"/>'
        for i in range(1, len(SLIDES) + 1)
    )
    return f"""<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<Relationships xmlns="http://schemas.openxmlformats.org/package/2006/relationships">
  {slide_rels_xml}
  <Relationship Id="rId{len(SLIDES)+1}" Type="http://schemas.openxmlformats.org/officeDocument/2006/relationships/slideMaster" Target="slideMasters/slideMaster1.xml"/>
  <Relationship Id="rId{len(SLIDES)+2}" Type="http://schemas.openxmlformats.org/officeDocument/2006/relationships/theme" Target="theme/theme1.xml"/>
</Relationships>"""


def slide_rels() -> str:
    return """<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<Relationships xmlns="http://schemas.openxmlformats.org/package/2006/relationships">
  <Relationship Id="rId1" Type="http://schemas.openxmlformats.org/officeDocument/2006/relationships/slideLayout" Target="../slideLayouts/slideLayout1.xml"/>
</Relationships>"""


def slide_master_rels() -> str:
    return """<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<Relationships xmlns="http://schemas.openxmlformats.org/package/2006/relationships">
  <Relationship Id="rId1" Type="http://schemas.openxmlformats.org/officeDocument/2006/relationships/slideLayout" Target="../slideLayouts/slideLayout1.xml"/>
  <Relationship Id="rId2" Type="http://schemas.openxmlformats.org/officeDocument/2006/relationships/theme" Target="../theme/theme1.xml"/>
</Relationships>"""


def slide_layout_rels() -> str:
    return """<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<Relationships xmlns="http://schemas.openxmlformats.org/package/2006/relationships">
  <Relationship Id="rId1" Type="http://schemas.openxmlformats.org/officeDocument/2006/relationships/slideMaster" Target="../slideMasters/slideMaster1.xml"/>
</Relationships>"""


def slide_master_xml() -> str:
    return """<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<p:sldMaster xmlns:a="http://schemas.openxmlformats.org/drawingml/2006/main"
             xmlns:r="http://schemas.openxmlformats.org/officeDocument/2006/relationships"
             xmlns:p="http://schemas.openxmlformats.org/presentationml/2006/main">
  <p:cSld><p:spTree><p:nvGrpSpPr><p:cNvPr id="1" name=""/><p:cNvGrpSpPr/><p:nvPr/></p:nvGrpSpPr><p:grpSpPr/></p:spTree></p:cSld>
  <p:clrMap bg1="lt1" tx1="dk1" bg2="lt2" tx2="dk2" accent1="accent1" accent2="accent2" accent3="accent3" accent4="accent4" accent5="accent5" accent6="accent6" hlink="hlink" folHlink="folHlink"/>
  <p:sldLayoutIdLst><p:sldLayoutId id="2147483649" r:id="rId1"/></p:sldLayoutIdLst>
</p:sldMaster>"""


def slide_layout_xml() -> str:
    return """<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<p:sldLayout xmlns:a="http://schemas.openxmlformats.org/drawingml/2006/main"
             xmlns:r="http://schemas.openxmlformats.org/officeDocument/2006/relationships"
             xmlns:p="http://schemas.openxmlformats.org/presentationml/2006/main" type="blank" preserve="1">
  <p:cSld name="Blank"><p:spTree><p:nvGrpSpPr><p:cNvPr id="1" name=""/><p:cNvGrpSpPr/><p:nvPr/></p:nvGrpSpPr><p:grpSpPr/></p:spTree></p:cSld>
</p:sldLayout>"""


def theme_xml() -> str:
    return """<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<a:theme xmlns:a="http://schemas.openxmlformats.org/drawingml/2006/main" name="EduGen">
  <a:themeElements>
    <a:clrScheme name="EduGen"><a:dk1><a:srgbClr val="111827"/></a:dk1><a:lt1><a:srgbClr val="FFFFFF"/></a:lt1><a:dk2><a:srgbClr val="1F2937"/></a:dk2><a:lt2><a:srgbClr val="F7FAFC"/></a:lt2><a:accent1><a:srgbClr val="2563EB"/></a:accent1><a:accent2><a:srgbClr val="059669"/></a:accent2><a:accent3><a:srgbClr val="D97706"/></a:accent3><a:accent4><a:srgbClr val="7C3AED"/></a:accent4><a:accent5><a:srgbClr val="DC2626"/></a:accent5><a:accent6><a:srgbClr val="0891B2"/></a:accent6><a:hlink><a:srgbClr val="2563EB"/></a:hlink><a:folHlink><a:srgbClr val="7C3AED"/></a:folHlink></a:clrScheme>
    <a:fontScheme name="EduGen"><a:majorFont><a:latin typeface="Aptos Display"/></a:majorFont><a:minorFont><a:latin typeface="Aptos"/></a:minorFont></a:fontScheme>
    <a:fmtScheme name="EduGen"><a:fillStyleLst/><a:lnStyleLst/><a:effectStyleLst/><a:bgFillStyleLst/></a:fmtScheme>
  </a:themeElements>
</a:theme>"""


if __name__ == "__main__":
    write_pptx(OUT)
    print(OUT)
