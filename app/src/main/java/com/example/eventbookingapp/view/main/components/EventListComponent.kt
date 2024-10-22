package com.example.eventbookingapp.view.main.components

import android.os.Build
import android.widget.Space
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.isPopupLayout
import com.example.eventbookingapp.R
import com.example.eventbookingapp.config.defaultPadding
import com.example.eventbookingapp.config.parseByNow
import com.example.eventbookingapp.view.entities.event.EventListEntity
import com.example.eventbookingapp.view.entities.event.EventLocationEntity
import com.example.eventbookingapp.view.entities.event.UserEntity
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun EventListComponent(
    modifier: Modifier,
    eventListEntity: EventListEntity,
    bookmarkLoading: Boolean,
    joinLoading: Boolean
) = with(eventListEntity) {
    Box(
        modifier = modifier
            .background(Color.White, RoundedCornerShape(15.dp)),
    ) {
        var isOverflow by remember {
            mutableStateOf(false)
        }
        Column(
            modifier = Modifier.padding(defaultPadding)
        ) {
            Title(nickname = author.nickname, date = createAt)

            Content(content = content) {
                isOverflow = true
            }

            if (isOverflow) {
                Text(text = "...")
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = "더 보기",
                    style = MaterialTheme.typography.bodySmall
                        .copy(color = Color.Gray)
                )
            }

            Spacer(modifier = Modifier.height(15.dp))

            Text(text = "일시 : ${DateTimeFormatter.ofPattern("yyyy년 MM월 dd일 a hh:mm").format(date)}")

            Spacer(modifier = Modifier.height(20.dp))

            Footer(
                capacity = capacity,
                participants = participants,
                isBookmarked = bookMarkFlag,
                isJoined = joinFlag,
                isBookmarkLoading = bookmarkLoading,
                isJoinLoading = joinLoading
            )
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
private fun Title(
    nickname: String,
    date: LocalDateTime
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = nickname,
            style = MaterialTheme.typography.titleMedium
        )
        Text(
            text = date.parseByNow(),
            style = MaterialTheme.typography.bodySmall
        )
    }
}

@Composable
private fun Content(
    content: String,
    onTextOverflow: () -> Unit
) {
    Text(
        modifier = Modifier.fillMaxWidth(),
        text = content,
        overflow = TextOverflow.Clip,
        maxLines = 4,
        onTextLayout = { textLayoutResult ->
            if (textLayoutResult.hasVisualOverflow) {
                onTextOverflow()
            }
        }
    )
}

@Composable
private fun Footer(
    capacity: Int,
    participants: Int,
    isBookmarked: Boolean,
    isJoined: Boolean,
    isBookmarkLoading: Boolean,
    isJoinLoading: Boolean
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            FooterIconBuilder(
                onIcon = painterResource(id = R.drawable.icon_join_outlined),
                offIcon = painterResource(id = R.drawable.icon_join_outlined),
                isLoading = isBookmarkLoading,
                onOff = isBookmarked
            ) {

            }

            val capacityStr = capacity.toString()
            val maxLength = if (capacityStr.length == 1) 2 else capacityStr.length

            Text(
                text = "${participants.toString().padStart(maxLength, '0')}/${
                    capacityStr.padStart(
                        maxLength,
                        '0'
                    )
                }"
            )
        }

        FooterIconBuilder(
            onIcon = painterResource(id = R.drawable.icon_bookmark_outlined),
            offIcon = painterResource(id = R.drawable.icon_bookmark_outlined),
            isLoading = isJoinLoading,
            onOff = isJoined
        ) {

        }
    }
}

@Composable
fun FooterIconBuilder(
    onIcon: Painter,
    offIcon: Painter,
    onOff: Boolean,
    isLoading: Boolean,
    onClicked: () -> Unit
) {
    when (isLoading) {
        true -> {
            LoadingComponent(
                modifier = Modifier.padding(horizontal = 5.dp)
            )
        }
        false -> {
            val iconResource = when (onOff) {
                true -> {
                    onIcon
                }
                false -> {
                    offIcon
                }
            }

            Icon(
                modifier = Modifier
                    .padding(horizontal = 5.dp)
                    .clickable {
                        onClicked()
                    },
                painter = iconResource,
                contentDescription = "미참여 버튼 아이콘"
            )
        }
    }
}

@Composable
fun LoadingComponent(
    modifier: Modifier = Modifier,
    size: Dp = 20.dp
) {
    CircularProgressIndicator(
        modifier = modifier.size(size)
    )
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview
@Composable
fun PreviewEventListComponent() {
    EventListComponent(
        modifier = Modifier.fillMaxWidth(),
        eventListEntity = EventListEntity(
            id = "id",
            author = UserEntity("id", "조현국"),
            content = "내용내용\n\n\n\nddd",
            location = EventLocationEntity(
                latitude = 1.0,
                longitude = 1.0,
                name = "우리집"
            ),
            date = LocalDateTime.now(),
            createAt = LocalDateTime.now(),
            capacity = 30,
            participants = 1,
            bookMarkFlag = false,
            joinFlag = false
        ),
        bookmarkLoading = true,
        joinLoading = true
    )
}