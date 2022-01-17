apkArch="$(apk --print-arch)"
case "$apkArch" in
    x86_64)
    echo "x86"
    ;;
    aarch64)
    echo "arm64"
    ;;
esac;