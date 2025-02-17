package com.plcoding.cleanarchitecturenoteapp.feature_note.domain.util

sealed class NoteOrder(val orderType: OrderType) {
    class Title(orderType: OrderType) : NoteOrder(orderType)
    class Color(orderType: OrderType) : NoteOrder(orderType)
    class Date(orderType: OrderType) : NoteOrder(orderType)
}
//sealed class NoteOrder() {
//    class Title(orderType: OrderType) : NoteOrder()
//    class Color(orderType: OrderType) : NoteOrder()
//    class Date(orderType: OrderType) : NoteOrder()
//}