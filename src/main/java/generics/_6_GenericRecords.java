package generics;

record crateRecord<T>(T contents) {

    @Override
    public T contents() {
        if (contents == null) {
            throw new IllegalStateException("missing contents");
        }
        return contents;
    }
}