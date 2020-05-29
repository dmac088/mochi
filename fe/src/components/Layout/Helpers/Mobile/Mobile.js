
export const isMobile = () => {
    return ((window.innerWidth
        || document.documentElement.clientWidth
        || document.body.clientWidth) <= 991);
}