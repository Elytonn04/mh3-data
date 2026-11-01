# MH3 Database para Android

App de consulta de Monster Hunter Tri que usa os dados deste projeto. É uma página web
(`app/src/main/assets/index.html`) dentro de um WebView, então funciona sem internet.
As fontes do Google só carregam quando há conexão; sem ela o app usa as fontes do celular.

## Baixar o APK

- **Aba Releases:** baixe `MH3-Database.apk` da release mais recente.
- **Aba Actions:** abra a execução mais recente de *Build Android APK* e baixe o arquivo
  em *Artifacts*.

No celular, permita a instalação de apps de fontes desconhecidas quando o Android pedir.

## Gerar um APK novo

- Pelo GitHub: *Actions → Build Android APK → Run workflow*.
- Para publicar na aba Releases, crie uma tag que comece com `v`, por exemplo `v1.0.1`.

## Sobre a assinatura

O arquivo `debug.keystore` é uma chave de teste pública, mantida no repositório para que
cada versão nova possa ser instalada por cima da anterior. Não use esta chave para publicar
o app em loja.

## Dados

O `index.html` é gerado a partir dos dados tipados de `src/` e dos arquivos de `raw-data/`.
