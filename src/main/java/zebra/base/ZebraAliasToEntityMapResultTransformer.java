package zebra.base;

import java.util.LinkedHashMap;
import java.util.Map;

import org.hibernate.transform.AliasedTupleSubsetResultTransformer;

public class ZebraAliasToEntityMapResultTransformer extends AliasedTupleSubsetResultTransformer {
	public static final ZebraAliasToEntityMapResultTransformer INSTANCE = new ZebraAliasToEntityMapResultTransformer();

	private ZebraAliasToEntityMapResultTransformer() {
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public Object transformTuple(Object[] tuple, String[] aliases) {
		Map result = new LinkedHashMap(tuple.length);
		for ( int i=0; i<tuple.length; i++ ) {
			String alias = aliases[i];
			if ( alias!=null ) {
				result.put( alias, tuple[i] );
			}
		}
		return result;
	}

	@Override
	public boolean isTransformedValueATupleElement(String[] aliases, int tupleLength) {
		return false;
	}

	private Object readResolve() {
		return INSTANCE;
	}
}