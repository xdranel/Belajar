import { route as routeFn } from 'ziggy-js'

declare module 'vue' {
    interface ComponentCustomProperties {
        route: typeof routeFn
    }
}
