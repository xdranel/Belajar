class Node:
    def __init__(self, value):
        self.value = value
        self.left = None
        self.right = None


def createTree(value):
    return Node(value)


def addNode(root, value):
    if root is None:
        return Node(value)

    if value < root.value:
        root.left = addNode(root.left, value)
    elif value > root.value:
        root.right = addNode(root.right, value)
    # jika value == root.value → tidak ditambahkan (no duplicates)

    return root


def displayInorder(root):
    if root is not None:
        displayInorder(root.left)
        print(root.value, end=" ")
        displayInorder(root.right)


def searchNode(root, value):
    if root is None:
        return False
    if root.value == value:
        return True
    if value < root.value:
        return searchNode(root.left, value)
    return searchNode(root.right, value)


def countNodes(root):
    if root is None:
        return 0
    return 1 + countNodes(root.left) + countNodes(root.right)


def getMinValue(root):
    if root is None:
        return None
    if root.left is None:
        return root.value
    return getMinValue(root.left)


def getMaxValue(root):
    if root is None:
        return None
    if root.right is None:
        return root.value
    return getMaxValue(root.right)


if __name__ == "__main__":
    root = createTree(50)

    values_to_add = [20, 30, 40, 50, 60, 70, 80]
    for val in values_to_add:
        root = addNode(root, val)

    print("Inorder Traversal:", end=" ")
    displayInorder(root)
    print()

    print(f"Jumlah Node      : {countNodes(root)}")
    print(f"Nilai Terkecil   : {getMinValue(root)}")
    print(f"Nilai Terbesar   : {getMaxValue(root)}")

    #    test_values = [80, 99, 180]
    #    for v in test_values:
    #    found = searchNode(root, v)
    #    print(f"Search {v}: {'Ditemukan' if found else 'Tidak ditemukan'}")
