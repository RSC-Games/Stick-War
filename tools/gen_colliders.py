def torect(x1, y1, x2, y2):
    p1a = (abs(x1), abs(y1))
    p2a = (abs(x2), abs(y2))

    center = ((x1 + x2)//2, (y1 + y2)//2)
    size = (abs(x1 - x2), abs(y1 - y2))
    print(f"center {center}")
    print(f"size {size}")
    print(f"sprites.add(new RectCollider(new Transform(new Rect({center[0]}, {center[1]}, {size[0]}, {size[1]})), \"PLATFORM\"));")


def direction(a, b):
    return -1 if a + b < 0 else 1 